package org.apache.flink.hadoopcompatibility;

import org.apache.flink.api.common.functions.MapFunction;
import org.apache.flink.api.common.serialization.DeserializationSchema;
import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.table.data.RowData;
import org.apache.flink.table.data.conversion.RowRowConverter;
import org.apache.flink.table.types.DataType;
import org.apache.flink.types.Row;
import org.apache.hadoop.io.BinaryComparable;

/**
 * @author cclient
 */
public class OutputRowFunction<K, V extends BinaryComparable> implements MapFunction<Tuple2<K, V>, Row> {


    private final DataType producedDataType;
    private final DeserializationSchema<RowData> deserializationSchema;

    public OutputRowFunction(DeserializationSchema<RowData> deserializationSchema, DataType producedDataType) {
        this.deserializationSchema = deserializationSchema;
        this.producedDataType = producedDataType;
    }

    public Row parseToRow(byte[] message) throws Exception {
        RowData rowData = deserializationSchema.deserialize(message);
        RowRowConverter rowRowConverter = RowRowConverter.create(producedDataType);
        rowRowConverter.toExternal(rowData);
        Row row = rowRowConverter.toExternal(rowData);
        return row;
    }

    @Override
    public Row map(Tuple2<K, V> vTuple2) throws Exception {
        return parseToRow(vTuple2.f1.getBytes());
    }
}