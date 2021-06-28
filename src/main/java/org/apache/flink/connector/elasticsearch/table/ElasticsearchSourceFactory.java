package org.apache.flink.connector.elasticsearch.table;

import org.apache.flink.configuration.ConfigOption;
import org.apache.flink.connector.elasticsearch.ElasticsearchTableOptions;
import org.apache.flink.table.api.TableSchema;
import org.apache.flink.table.descriptors.DescriptorProperties;
import org.apache.flink.table.factories.BatchTableSourceFactory;
import org.apache.flink.table.sources.BatchTableSource;
import org.apache.flink.table.utils.TableSchemaUtils;
import org.apache.flink.types.Row;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hdfs.HdfsConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.apache.flink.table.descriptors.ConnectorDescriptorValidator.CONNECTOR_PROPERTY_VERSION;
import static org.apache.flink.table.descriptors.ConnectorDescriptorValidator.CONNECTOR_TYPE;
import static org.apache.flink.table.descriptors.DescriptorProperties.*;
import static org.apache.flink.table.descriptors.Schema.*;

/**
 * @author cclient
 */
public class ElasticsearchSourceFactory implements BatchTableSourceFactory<Row> {

    private static final Logger log = LoggerFactory.getLogger(ElasticsearchSourceFactory.class);

    public void setConf(Configuration conf, DescriptorProperties descriptorProperties, String key) {
        if (descriptorProperties.containsKey(key)) {
            conf.set(key, descriptorProperties.getString(key));
        }
    }

    public Configuration buildConfiguration(DescriptorProperties descriptorProperties) {
        Configuration conf = new Configuration();
        for (ConfigOption<String> esConfigOption : ElasticsearchTableOptions.ES_CONFIG_OPTIONS) {
            setConf(conf, descriptorProperties, esConfigOption.key());
        }
        return conf;
    }

    @Override
    public BatchTableSource<Row> createBatchTableSource(Map<String, String> properties) {
        final DescriptorProperties descriptorProperties = new DescriptorProperties(true);
        descriptorProperties.putProperties(properties);
        Configuration conf = buildConfiguration(descriptorProperties);
        TableSchema schema =
                TableSchemaUtils.getPhysicalSchema(descriptorProperties.getTableSchema(SCHEMA));
        return ElasticsearchTableSource.builder()
                .setConfiguration(conf)
                .setSchema(schema).build();
    }

    @Override
    public Map<String, String> requiredContext() {
        Map<String, String> context = new HashMap<>();
        context.put(CONNECTOR_TYPE, "elasticsearch"); // elasticsearch
        context.put(CONNECTOR_PROPERTY_VERSION, "1"); // backwards compatibility
        return context;
    }

    @Override
    public List<String> supportedProperties() {
        List<String> properties = new ArrayList<>();
        for (ConfigOption<String> esConfigOption : ElasticsearchTableOptions.ES_CONFIG_OPTIONS) {
            properties.add(esConfigOption.key());
        }
        // schema
        properties.add(SCHEMA + ".#." + SCHEMA_DATA_TYPE);
        properties.add(SCHEMA + ".#." + SCHEMA_TYPE);
        properties.add(SCHEMA + ".#." + SCHEMA_NAME);
        // computed column
        properties.add(SCHEMA + ".#." + EXPR);

        // watermark
        properties.add(SCHEMA + "." + WATERMARK + ".#." + WATERMARK_ROWTIME);
        properties.add(SCHEMA + "." + WATERMARK + ".#." + WATERMARK_STRATEGY_EXPR);
        properties.add(SCHEMA + "." + WATERMARK + ".#." + WATERMARK_STRATEGY_DATA_TYPE);

        // table constraint
        properties.add(SCHEMA + "." + DescriptorProperties.PRIMARY_KEY_NAME);
        properties.add(SCHEMA + "." + DescriptorProperties.PRIMARY_KEY_COLUMNS);

        return properties;
    }
}
