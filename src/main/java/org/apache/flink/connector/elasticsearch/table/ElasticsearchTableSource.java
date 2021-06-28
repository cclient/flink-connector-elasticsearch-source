/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.apache.flink.connector.elasticsearch.table;

import org.apache.flink.api.common.serialization.DeserializationSchema;
import org.apache.flink.api.java.DataSet;
import org.apache.flink.api.java.ExecutionEnvironment;
import org.apache.flink.api.java.hadoop.mapreduce.HadoopInputFormat;
import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.api.java.typeutils.RowTypeInfo;
import org.apache.flink.formats.common.TimestampFormat;
import org.apache.flink.formats.json.JsonRowDataDeserializationSchema;
import org.apache.flink.hadoopcompatibility.HadoopInputs;
import org.apache.flink.hadoopcompatibility.OutputRowFunction;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.table.api.TableSchema;
import org.apache.flink.table.data.RowData;
import org.apache.flink.table.runtime.typeutils.InternalTypeInfo;
import org.apache.flink.table.sources.BatchTableSource;
import org.apache.flink.table.sources.StreamTableSource;
import org.apache.flink.table.types.DataType;
import org.apache.flink.table.types.inference.TypeTransformations;
import org.apache.flink.table.types.logical.LogicalType;
import org.apache.flink.table.types.logical.RowType;
import org.apache.flink.table.types.utils.DataTypeUtils;
import org.apache.flink.table.utils.TableConnectorUtils;
import org.apache.flink.types.Row;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.output.NullOutputFormat;
import org.elasticsearch.hadoop.mr.EsInputFormat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

import static org.apache.flink.table.types.utils.TypeConversions.fromDataTypeToLegacyInfo;
import static org.apache.flink.util.Preconditions.checkNotNull;

/**
 * @author cclient
 */
public class ElasticsearchTableSource
        implements BatchTableSource<Row>, StreamTableSource<Row> {
    private static final Logger log = LoggerFactory.getLogger(ElasticsearchTableSource.class);

    private final TableSchema schema;
    private final int[] selectFields;
    private final DataType producedDataType;
    private final RowType rowType;
    private final DeserializationSchema<RowData> deserializationSchema;
    private final HadoopInputFormat<Text, Text> hadoopInputFormat;
    private final boolean failOnMissingField = false;
    private final boolean ignoreParseErrors = false;
    private final TimestampFormat timestampFormat = TimestampFormat.ISO_8601;

    private ElasticsearchTableSource(Configuration configuration,
                                     TableSchema schema) {
        this(configuration, schema, null);
    }

    private ElasticsearchTableSource(Configuration configuration,
                                     TableSchema schema,
                                     int[] selectFields) {
        this.schema = schema;
        this.selectFields = selectFields;
        final DataType[] schemaDataTypes = schema.getFieldDataTypes();
        final String[] schemaFieldNames = schema.getFieldNames();
        if (selectFields != null) {
            DataType[] dataTypes = new DataType[selectFields.length];
            String[] fieldNames = new String[selectFields.length];
            for (int i = 0; i < selectFields.length; i++) {
                dataTypes[i] = schemaDataTypes[selectFields[i]];
                fieldNames[i] = schemaFieldNames[selectFields[i]];
            }
            this.producedDataType =
                    TableSchema.builder().fields(fieldNames, dataTypes).build().toRowDataType();
            this.rowType = buildRowType(dataTypes, fieldNames);
        } else {
            this.producedDataType = schema.toRowDataType();
            this.rowType = buildRowType(schema);
        }
        Job job = buildJob(configuration);
        this.hadoopInputFormat = HadoopInputs.createHadoopInput(new EsInputFormat<Text, Text>(), Text.class, Text.class, job);
        deserializationSchema = new JsonRowDataDeserializationSchema(rowType, InternalTypeInfo.of(rowType), failOnMissingField, ignoreParseErrors, timestampFormat);
    }

    public static Builder builder() {
        return new Builder();
    }

    private Job buildJob(Configuration configuration) {
        Job job = null;
        try {
            //use Text and json parse,do not use MapWritable
            configuration.set("es.output.json", "true");
            //es metadata _index,_type,_id
            configuration.set("es.read.metadata", "true");
            job = new Job(configuration);
            //this.job.waitForCompletion(true);
        } catch (IOException e) {
            e.printStackTrace();
        }
        job.setInputFormatClass(EsInputFormat.class);
        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(Text.class);
        job.setOutputFormatClass(NullOutputFormat.class);
        return job;
    }

    public RowType buildRowType(TableSchema schema) {
        return buildRowType(schema.getFieldDataTypes(), schema.getFieldNames());
    }

    public RowType buildRowType(DataType[] fieldDataTypes, String[] fieldNames) {
        LogicalType[] logicalTypes = new LogicalType[fieldDataTypes.length];
        for (int i = 0; i < fieldDataTypes.length; i++) {
            logicalTypes[i] = fieldDataTypes[i].getLogicalType();
        }
        return RowType.of(logicalTypes, fieldNames);
    }

    @Override
    public DataType getProducedDataType() {
        return producedDataType;
    }

    @Override
    public TableSchema getTableSchema() {
        return schema;
    }

    @Override
    public String explainSource() {
        final RowTypeInfo rowTypeInfo = (RowTypeInfo) fromDataTypeToLegacyInfo(producedDataType);
        return TableConnectorUtils.generateRuntimeName(getClass(), rowTypeInfo.getFieldNames());
    }

    @Override
    public DataSet<Row> getDataSet(ExecutionEnvironment executionEnvironment) {
        ExecutionEnvironment env = ExecutionEnvironment.getExecutionEnvironment();
        DataSet<Tuple2<Text, Text>> input =
                env.createInput(this.hadoopInputFormat);
        DataSet<Row> rowDataSet = input.map(new OutputRowFunction<Text, Text>(deserializationSchema, producedDataType)).returns(new RowTypeInfo(this.schema.getFieldTypes(), this.schema.getFieldNames()));
        return rowDataSet;
    }

    @Override
    public DataStream<Row> getDataStream(StreamExecutionEnvironment execEnv) {
        return execEnv.createInput(this.hadoopInputFormat)
                .map(new OutputRowFunction<Text, Text>(deserializationSchema, producedDataType), new RowTypeInfo(this.schema.getFieldTypes(), this.schema.getFieldNames()));
    }

    /**
     * Builder for a {@link ElasticsearchTableSource}.
     */
    public static class Builder {
        protected Configuration configuration;

        protected TableSchema schema;

        public static TableSchema normalizeTableSchema(TableSchema schema) {
            TableSchema.Builder physicalSchemaBuilder = TableSchema.builder();
            schema.getTableColumns()
                    .forEach(
                            c -> {
                                if (c.isPhysical()) {
                                    final DataType type =
                                            DataTypeUtils.transform(
                                                    c.getType(), TypeTransformations.timeToSqlTypes());
                                    physicalSchemaBuilder.field(c.getName(), type);
                                }
                            });
            return physicalSchemaBuilder.build();
        }

        /**
         * required, table schema of this table source.
         */
        public Builder setSchema(TableSchema schema) {
            this.schema = normalizeTableSchema(schema);
            return this;
        }

        public Builder setConfiguration(Configuration configuration) {
            this.configuration = configuration;
            return this;
        }

        /**
         * Finalizes the configuration and checks validity.
         *
         * @return Configured JdbcTableSource
         */
        public ElasticsearchTableSource build() {
            checkNotNull(schema, "No schema supplied.");
            return new ElasticsearchTableSource(configuration, schema);
        }
    }
}
