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

package org.apache.flink.connector.elasticsearch;

import org.apache.flink.configuration.ConfigOption;
import org.apache.flink.configuration.ConfigOptions;
import org.elasticsearch.hadoop.cfg.ConfigurationOptions;
import org.elasticsearch.hadoop.serialization.field.DateIndexFormatter;
import org.elasticsearch.hadoop.serialization.field.DefaultIndexExtractor;
import org.elasticsearch.hadoop.serialization.field.DefaultParamsExtractor;

/**
 * @author cclient
 */
public class ElasticsearchTableOptions {
    public static final ConfigOption<String> ES_NODES = ConfigOptions.key(ConfigurationOptions.ES_NODES).stringType().defaultValue(ConfigurationOptions.ES_NODES_DEFAULT).withDescription("");
    public static final ConfigOption<String> ES_NODES_DISCOVERY = ConfigOptions.key(ConfigurationOptions.ES_NODES_DISCOVERY).stringType().defaultValue(ConfigurationOptions.ES_NODES_DISCOVERY_DEFAULT).withDescription("");
    public static final ConfigOption<String> ES_PORT = ConfigOptions.key(ConfigurationOptions.ES_PORT).stringType().defaultValue(ConfigurationOptions.ES_PORT_DEFAULT).withDescription("");
    public static final ConfigOption<String> ES_NODES_PATH_PREFIX = ConfigOptions.key(ConfigurationOptions.ES_NODES_PATH_PREFIX).stringType().defaultValue(ConfigurationOptions.ES_NODES_PATH_PREFIX_DEFAULT).withDescription("");
    public static final ConfigOption<String> ES_NODES_CLIENT_ONLY = ConfigOptions.key(ConfigurationOptions.ES_NODES_CLIENT_ONLY).stringType().defaultValue(ConfigurationOptions.ES_NODES_CLIENT_ONLY_DEFAULT).withDescription("");
    public static final ConfigOption<String> ES_NODES_DATA_ONLY = ConfigOptions.key(ConfigurationOptions.ES_NODES_DATA_ONLY).stringType().defaultValue(ConfigurationOptions.ES_NODES_DATA_ONLY_DEFAULT).withDescription("");
    public static final ConfigOption<String> ES_NODES_INGEST_ONLY = ConfigOptions.key(ConfigurationOptions.ES_NODES_INGEST_ONLY).stringType().defaultValue(ConfigurationOptions.ES_NODES_INGEST_ONLY_DEFAULT).withDescription("");
    public static final ConfigOption<String> ES_NODES_WAN_ONLY = ConfigOptions.key(ConfigurationOptions.ES_NODES_WAN_ONLY).stringType().defaultValue(ConfigurationOptions.ES_NODES_WAN_ONLY_DEFAULT).withDescription("");
    public static final ConfigOption<String> ES_BATCH_SIZE_BYTES = ConfigOptions.key(ConfigurationOptions.ES_BATCH_SIZE_BYTES).stringType().defaultValue(ConfigurationOptions.ES_BATCH_SIZE_BYTES_DEFAULT).withDescription("");
    public static final ConfigOption<String> ES_BATCH_SIZE_ENTRIES = ConfigOptions.key(ConfigurationOptions.ES_BATCH_SIZE_ENTRIES).stringType().defaultValue(ConfigurationOptions.ES_BATCH_SIZE_ENTRIES_DEFAULT).withDescription("");
    public static final ConfigOption<String> ES_BATCH_FLUSH_MANUAL = ConfigOptions.key(ConfigurationOptions.ES_BATCH_FLUSH_MANUAL).stringType().defaultValue(ConfigurationOptions.ES_BATCH_FLUSH_MANUAL_DEFAULT).withDescription("");
    public static final ConfigOption<String> ES_BATCH_WRITE_REFRESH = ConfigOptions.key(ConfigurationOptions.ES_BATCH_WRITE_REFRESH).stringType().defaultValue(ConfigurationOptions.ES_BATCH_WRITE_REFRESH_DEFAULT).withDescription("");
    public static final ConfigOption<String> ES_BATCH_WRITE_RETRY_COUNT = ConfigOptions.key(ConfigurationOptions.ES_BATCH_WRITE_RETRY_COUNT).stringType().defaultValue(ConfigurationOptions.ES_BATCH_WRITE_RETRY_COUNT_DEFAULT).withDescription("");
    public static final ConfigOption<String> ES_BATCH_WRITE_RETRY_LIMIT = ConfigOptions.key(ConfigurationOptions.ES_BATCH_WRITE_RETRY_LIMIT).stringType().defaultValue(ConfigurationOptions.ES_BATCH_WRITE_RETRY_LIMIT_DEFAULT).withDescription("");
    public static final ConfigOption<String> ES_BATCH_WRITE_RETRY_WAIT = ConfigOptions.key(ConfigurationOptions.ES_BATCH_WRITE_RETRY_WAIT).stringType().defaultValue(ConfigurationOptions.ES_BATCH_WRITE_RETRY_WAIT_DEFAULT).withDescription("");
    public static final ConfigOption<String> ES_BATCH_WRITE_RETRY_POLICY = ConfigOptions.key(ConfigurationOptions.ES_BATCH_WRITE_RETRY_POLICY).stringType().defaultValue(ConfigurationOptions.ES_BATCH_WRITE_RETRY_POLICY_DEFAULT).withDescription("");
    public static final ConfigOption<String> ES_HTTP_TIMEOUT = ConfigOptions.key(ConfigurationOptions.ES_HTTP_TIMEOUT).stringType().defaultValue(ConfigurationOptions.ES_HTTP_TIMEOUT_DEFAULT).withDescription("");
    public static final ConfigOption<String> ES_HTTP_RETRIES = ConfigOptions.key(ConfigurationOptions.ES_HTTP_RETRIES).stringType().defaultValue(ConfigurationOptions.ES_HTTP_RETRIES_DEFAULT).withDescription("");
    public static final ConfigOption<String> ES_SCROLL_KEEPALIVE = ConfigOptions.key(ConfigurationOptions.ES_SCROLL_KEEPALIVE).stringType().defaultValue(ConfigurationOptions.ES_SCROLL_KEEPALIVE_DEFAULT).withDescription("");
    public static final ConfigOption<String> ES_SCROLL_SIZE = ConfigOptions.key(ConfigurationOptions.ES_SCROLL_SIZE).stringType().defaultValue(ConfigurationOptions.ES_SCROLL_SIZE_DEFAULT).withDescription("");
    public static final ConfigOption<String> ES_SCROLL_LIMIT = ConfigOptions.key(ConfigurationOptions.ES_SCROLL_LIMIT).stringType().defaultValue(ConfigurationOptions.ES_SCROLL_LIMIT_DEFAULT).withDescription("");
    public static final ConfigOption<String> ES_HEART_BEAT_LEAD = ConfigOptions.key(ConfigurationOptions.ES_HEART_BEAT_LEAD).stringType().defaultValue(ConfigurationOptions.ES_HEART_BEAT_LEAD_DEFAULT).withDescription("");
    public static final ConfigOption<String> ES_INPUT_JSON = ConfigOptions.key(ConfigurationOptions.ES_INPUT_JSON).stringType().defaultValue(ConfigurationOptions.ES_INPUT_JSON_DEFAULT).withDescription("");
    public static final ConfigOption<String> ES_INDEX_AUTO_CREATE = ConfigOptions.key(ConfigurationOptions.ES_INDEX_AUTO_CREATE).stringType().defaultValue(ConfigurationOptions.ES_INDEX_AUTO_CREATE_DEFAULT).withDescription("");
    public static final ConfigOption<String> ES_INDEX_READ_MISSING_AS_EMPTY = ConfigOptions.key(ConfigurationOptions.ES_INDEX_READ_MISSING_AS_EMPTY).stringType().defaultValue(ConfigurationOptions.ES_INDEX_READ_MISSING_AS_EMPTY_DEFAULT).withDescription("");
    public static final ConfigOption<String> ES_INDEX_READ_ALLOW_RED_STATUS = ConfigOptions.key(ConfigurationOptions.ES_INDEX_READ_ALLOW_RED_STATUS).stringType().defaultValue(ConfigurationOptions.ES_INDEX_READ_ALLOW_RED_STATUS_DEFAULT).withDescription("");
    public static final ConfigOption<String> ES_READ_SHARD_PREFERENCE = ConfigOptions.key(ConfigurationOptions.ES_READ_SHARD_PREFERENCE).stringType().defaultValue(ConfigurationOptions.ES_READ_SHARD_PREFERENCE_DEFAULT).withDescription("");
    public static final ConfigOption<String> ES_MAPPING_CONSTANT_AUTO_QUOTE = ConfigOptions.key(ConfigurationOptions.ES_MAPPING_CONSTANT_AUTO_QUOTE).stringType().defaultValue(ConfigurationOptions.ES_MAPPING_CONSTANT_AUTO_QUOTE_DEFAULT).withDescription("");
    public static final ConfigOption<String> ES_MAPPING_DATE_RICH_OBJECT = ConfigOptions.key(ConfigurationOptions.ES_MAPPING_DATE_RICH_OBJECT).stringType().defaultValue(ConfigurationOptions.ES_MAPPING_DATE_RICH_OBJECT_DEFAULT).withDescription("");
    public static final ConfigOption<String> ES_MAPPING_INCLUDE = ConfigOptions.key(ConfigurationOptions.ES_MAPPING_INCLUDE).stringType().defaultValue(ConfigurationOptions.ES_MAPPING_INCLUDE_DEFAULT).withDescription("");
    public static final ConfigOption<String> ES_MAPPING_EXCLUDE = ConfigOptions.key(ConfigurationOptions.ES_MAPPING_EXCLUDE).stringType().defaultValue(ConfigurationOptions.ES_MAPPING_EXCLUDE_DEFAULT).withDescription("");
    public static final ConfigOption<String> ES_INGEST_PIPELINE = ConfigOptions.key(ConfigurationOptions.ES_INGEST_PIPELINE).stringType().defaultValue(ConfigurationOptions.ES_INGEST_PIPELINE_DEFAULT).withDescription("");
    public static final ConfigOption<String> ES_SPARK_DATAFRAME_WRITE_NULL_VALUES = ConfigOptions.key(ConfigurationOptions.ES_SPARK_DATAFRAME_WRITE_NULL_VALUES).stringType().defaultValue(ConfigurationOptions.ES_SPARK_DATAFRAME_WRITE_NULL_VALUES_DEFAULT).withDescription("");
    public static final ConfigOption<String> ES_READ_FIELD_EMPTY_AS_NULL = ConfigOptions.key(ConfigurationOptions.ES_READ_FIELD_EMPTY_AS_NULL).stringType().defaultValue(ConfigurationOptions.ES_READ_FIELD_EMPTY_AS_NULL_DEFAULT).withDescription("");
    public static final ConfigOption<String> ES_READ_FIELD_VALIDATE_PRESENCE = ConfigOptions.key(ConfigurationOptions.ES_READ_FIELD_VALIDATE_PRESENCE).stringType().defaultValue(ConfigurationOptions.ES_READ_FIELD_VALIDATE_PRESENCE_DEFAULT).withDescription("");
    public static final ConfigOption<String> ES_READ_METADATA = ConfigOptions.key(ConfigurationOptions.ES_READ_METADATA).stringType().defaultValue(ConfigurationOptions.ES_READ_METADATA_DEFAULT).withDescription("");
    public static final ConfigOption<String> ES_READ_METADATA_FIELD = ConfigOptions.key(ConfigurationOptions.ES_READ_METADATA_FIELD).stringType().defaultValue(ConfigurationOptions.ES_READ_METADATA_FIELD_DEFAULT).withDescription("");
    public static final ConfigOption<String> ES_READ_METADATA_VERSION = ConfigOptions.key(ConfigurationOptions.ES_READ_METADATA_VERSION).stringType().defaultValue(ConfigurationOptions.ES_READ_METADATA_VERSION_DEFAULT).withDescription("");
    public static final ConfigOption<String> ES_READ_UNMAPPED_FIELDS_IGNORE = ConfigOptions.key(ConfigurationOptions.ES_READ_UNMAPPED_FIELDS_IGNORE).stringType().defaultValue(ConfigurationOptions.ES_READ_UNMAPPED_FIELDS_IGNORE_DEFAULT).withDescription("");
    public static final ConfigOption<String> ES_WRITE_OPERATION = ConfigOptions.key(ConfigurationOptions.ES_WRITE_OPERATION).stringType().defaultValue(ConfigurationOptions.ES_WRITE_OPERATION_DEFAULT).withDescription("");
    public static final ConfigOption<String> ES_UPDATE_RETRY_ON_CONFLICT = ConfigOptions.key(ConfigurationOptions.ES_UPDATE_RETRY_ON_CONFLICT).stringType().defaultValue(ConfigurationOptions.ES_UPDATE_RETRY_ON_CONFLICT_DEFAULT).withDescription("");
    public static final ConfigOption<String> ES_OUTPUT_JSON = ConfigOptions.key(ConfigurationOptions.ES_OUTPUT_JSON).stringType().defaultValue(ConfigurationOptions.ES_OUTPUT_JSON_DEFAULT).withDescription("");
    public static final ConfigOption<String> ES_NET_TRANSPORT_POOLING_EXPIRATION_TIMEOUT = ConfigOptions.key(ConfigurationOptions.ES_NET_TRANSPORT_POOLING_EXPIRATION_TIMEOUT).stringType().defaultValue(ConfigurationOptions.ES_NET_TRANSPORT_POOLING_EXPIRATION_TIMEOUT_DEFAULT).withDescription("");
    public static final ConfigOption<String> ES_NET_USE_SSL = ConfigOptions.key(ConfigurationOptions.ES_NET_USE_SSL).stringType().defaultValue(ConfigurationOptions.ES_NET_USE_SSL_DEFAULT).withDescription("");
    public static final ConfigOption<String> ES_NET_SSL_PROTOCOL = ConfigOptions.key(ConfigurationOptions.ES_NET_SSL_PROTOCOL).stringType().defaultValue(ConfigurationOptions.ES_NET_SSL_PROTOCOL_DEFAULT).withDescription("");
    public static final ConfigOption<String> ES_NET_SSL_KEYSTORE_TYPE = ConfigOptions.key(ConfigurationOptions.ES_NET_SSL_KEYSTORE_TYPE).stringType().defaultValue(ConfigurationOptions.ES_NET_SSL_KEYSTORE_TYPE_DEFAULT).withDescription("");
    public static final ConfigOption<String> ES_NET_SSL_CERT_ALLOW_SELF_SIGNED = ConfigOptions.key(ConfigurationOptions.ES_NET_SSL_CERT_ALLOW_SELF_SIGNED).stringType().defaultValue(ConfigurationOptions.ES_NET_SSL_CERT_ALLOW_SELF_SIGNED_DEFAULT).withDescription("");
    public static final ConfigOption<String> ES_NET_SPNEGO_AUTH_MUTUAL = ConfigOptions.key(ConfigurationOptions.ES_NET_SPNEGO_AUTH_MUTUAL).stringType().defaultValue(ConfigurationOptions.ES_NET_SPNEGO_AUTH_MUTUAL_DEFAULT).withDescription("");
    public static final ConfigOption<String> ES_NET_PROXY_HTTP_USE_SYSTEM_PROPS = ConfigOptions.key(ConfigurationOptions.ES_NET_PROXY_HTTP_USE_SYSTEM_PROPS).stringType().defaultValue(ConfigurationOptions.ES_NET_PROXY_HTTP_USE_SYSTEM_PROPS_DEFAULT).withDescription("");
    public static final ConfigOption<String> ES_NET_PROXY_HTTPS_USE_SYSTEM_PROPS = ConfigOptions.key(ConfigurationOptions.ES_NET_PROXY_HTTPS_USE_SYSTEM_PROPS).stringType().defaultValue(ConfigurationOptions.ES_NET_PROXY_HTTPS_USE_SYSTEM_PROPS_DEFAULT).withDescription("");
    public static final ConfigOption<String> ES_NET_PROXY_SOCKS_USE_SYSTEM_PROPS = ConfigOptions.key(ConfigurationOptions.ES_NET_PROXY_SOCKS_USE_SYSTEM_PROPS).stringType().defaultValue(ConfigurationOptions.ES_NET_PROXY_SOCKS_USE_SYSTEM_PROPS_DEFAULT).withDescription("");
    public static final ConfigOption<String> ES_MAPPING_DEFAULT_INDEX_EXTRACTOR_CLASS = ConfigOptions.key(ConfigurationOptions.ES_MAPPING_DEFAULT_INDEX_EXTRACTOR_CLASS).stringType().defaultValue(DefaultIndexExtractor.class.getName()).withDescription("");
    public static final ConfigOption<String> ES_MAPPING_DEFAULT_INDEX_FORMATTER_CLASS = ConfigOptions.key(ConfigurationOptions.ES_MAPPING_DEFAULT_INDEX_FORMATTER_CLASS).stringType().defaultValue(DateIndexFormatter.class.getName()).withDescription("");
    public static final ConfigOption<String> ES_MAPPING_PARAMS_DEFAULT_EXTRACTOR_CLASS = ConfigOptions.key(ConfigurationOptions.ES_MAPPING_PARAMS_DEFAULT_EXTRACTOR_CLASS).stringType().defaultValue(DefaultParamsExtractor.class.getName()).withDescription("");
    public static final ConfigOption<String> ES_RESOURCE = ConfigOptions.key(ConfigurationOptions.ES_RESOURCE).stringType().noDefaultValue().withDescription("");
    public static final ConfigOption<String> ES_RESOURCE_READ = ConfigOptions.key(ConfigurationOptions.ES_RESOURCE_READ).stringType().noDefaultValue().withDescription("");
    public static final ConfigOption<String> ES_RESOURCE_WRITE = ConfigOptions.key(ConfigurationOptions.ES_RESOURCE_WRITE).stringType().noDefaultValue().withDescription("");
    public static final ConfigOption<String> ES_QUERY = ConfigOptions.key(ConfigurationOptions.ES_QUERY).stringType().noDefaultValue().withDescription("");
    public static final ConfigOption<String> ES_NODES_RESOLVE_HOST_NAME = ConfigOptions.key(ConfigurationOptions.ES_NODES_RESOLVE_HOST_NAME).stringType().noDefaultValue().withDescription("");
    public static final ConfigOption<String> ES_KEYSTORE_LOCATION = ConfigOptions.key(ConfigurationOptions.ES_KEYSTORE_LOCATION).stringType().noDefaultValue().withDescription("");
    public static final ConfigOption<String> ES_BATCH_WRITE_RETRY_POLICY_NONE = ConfigOptions.key(ConfigurationOptions.ES_BATCH_WRITE_RETRY_POLICY_NONE).stringType().noDefaultValue().withDescription("");
    public static final ConfigOption<String> ES_BATCH_WRITE_RETRY_POLICY_SIMPLE = ConfigOptions.key(ConfigurationOptions.ES_BATCH_WRITE_RETRY_POLICY_SIMPLE).stringType().noDefaultValue().withDescription("");
    public static final ConfigOption<String> ES_SERIALIZATION_WRITER_VALUE_CLASS = ConfigOptions.key(ConfigurationOptions.ES_SERIALIZATION_WRITER_VALUE_CLASS).stringType().noDefaultValue().withDescription("");
    public static final ConfigOption<String> ES_SERIALIZATION_WRITER_BYTES_CLASS = ConfigOptions.key(ConfigurationOptions.ES_SERIALIZATION_WRITER_BYTES_CLASS).stringType().noDefaultValue().withDescription("");
    public static final ConfigOption<String> ES_SERIALIZATION_READER_VALUE_CLASS = ConfigOptions.key(ConfigurationOptions.ES_SERIALIZATION_READER_VALUE_CLASS).stringType().noDefaultValue().withDescription("");
    public static final ConfigOption<String> ES_MAX_DOCS_PER_PARTITION = ConfigOptions.key(ConfigurationOptions.ES_MAX_DOCS_PER_PARTITION).stringType().noDefaultValue().withDescription("");
    public static final ConfigOption<String> ES_MAPPING_METADATA_EXTRACTOR_CLASS = ConfigOptions.key(ConfigurationOptions.ES_MAPPING_METADATA_EXTRACTOR_CLASS).stringType().noDefaultValue().withDescription("");
    public static final ConfigOption<String> ES_MAPPING_ID = ConfigOptions.key(ConfigurationOptions.ES_MAPPING_ID).stringType().noDefaultValue().withDescription("");
    public static final ConfigOption<String> ES_MAPPING_ID_EXTRACTOR_CLASS = ConfigOptions.key(ConfigurationOptions.ES_MAPPING_ID_EXTRACTOR_CLASS).stringType().noDefaultValue().withDescription("");
    public static final ConfigOption<String> ES_MAPPING_PARENT = ConfigOptions.key(ConfigurationOptions.ES_MAPPING_PARENT).stringType().noDefaultValue().withDescription("");
    public static final ConfigOption<String> ES_MAPPING_PARENT_EXTRACTOR_CLASS = ConfigOptions.key(ConfigurationOptions.ES_MAPPING_PARENT_EXTRACTOR_CLASS).stringType().noDefaultValue().withDescription("");
    public static final ConfigOption<String> ES_MAPPING_JOIN = ConfigOptions.key(ConfigurationOptions.ES_MAPPING_JOIN).stringType().noDefaultValue().withDescription("");
    public static final ConfigOption<String> ES_MAPPING_JOIN_EXTRACTOR_CLASS = ConfigOptions.key(ConfigurationOptions.ES_MAPPING_JOIN_EXTRACTOR_CLASS).stringType().noDefaultValue().withDescription("");
    public static final ConfigOption<String> ES_MAPPING_VERSION = ConfigOptions.key(ConfigurationOptions.ES_MAPPING_VERSION).stringType().noDefaultValue().withDescription("");
    public static final ConfigOption<String> ES_MAPPING_VERSION_EXTRACTOR_CLASS = ConfigOptions.key(ConfigurationOptions.ES_MAPPING_VERSION_EXTRACTOR_CLASS).stringType().noDefaultValue().withDescription("");
    public static final ConfigOption<String> ES_MAPPING_ROUTING = ConfigOptions.key(ConfigurationOptions.ES_MAPPING_ROUTING).stringType().noDefaultValue().withDescription("");
    public static final ConfigOption<String> ES_MAPPING_ROUTING_EXTRACTOR_CLASS = ConfigOptions.key(ConfigurationOptions.ES_MAPPING_ROUTING_EXTRACTOR_CLASS).stringType().noDefaultValue().withDescription("");
    public static final ConfigOption<String> ES_MAPPING_TTL = ConfigOptions.key(ConfigurationOptions.ES_MAPPING_TTL).stringType().noDefaultValue().withDescription("");
    public static final ConfigOption<String> ES_MAPPING_TTL_EXTRACTOR_CLASS = ConfigOptions.key(ConfigurationOptions.ES_MAPPING_TTL_EXTRACTOR_CLASS).stringType().noDefaultValue().withDescription("");
    public static final ConfigOption<String> ES_MAPPING_TIMESTAMP = ConfigOptions.key(ConfigurationOptions.ES_MAPPING_TIMESTAMP).stringType().noDefaultValue().withDescription("");
    public static final ConfigOption<String> ES_MAPPING_TIMESTAMP_EXTRACTOR_CLASS = ConfigOptions.key(ConfigurationOptions.ES_MAPPING_TIMESTAMP_EXTRACTOR_CLASS).stringType().noDefaultValue().withDescription("");
    public static final ConfigOption<String> ES_MAPPING_INDEX_EXTRACTOR_CLASS = ConfigOptions.key(ConfigurationOptions.ES_MAPPING_INDEX_EXTRACTOR_CLASS).stringType().noDefaultValue().withDescription("");
    public static final ConfigOption<String> ES_MAPPING_INDEX_FORMATTER_CLASS = ConfigOptions.key(ConfigurationOptions.ES_MAPPING_INDEX_FORMATTER_CLASS).stringType().noDefaultValue().withDescription("");
    public static final ConfigOption<String> ES_MAPPING_PARAMS_EXTRACTOR_CLASS = ConfigOptions.key(ConfigurationOptions.ES_MAPPING_PARAMS_EXTRACTOR_CLASS).stringType().noDefaultValue().withDescription("");
    public static final ConfigOption<String> ES_MAPPING_VERSION_TYPE = ConfigOptions.key(ConfigurationOptions.ES_MAPPING_VERSION_TYPE).stringType().noDefaultValue().withDescription("");
    public static final ConfigOption<String> ES_MAPPING_VERSION_TYPE_INTERNAL = ConfigOptions.key(ConfigurationOptions.ES_MAPPING_VERSION_TYPE_INTERNAL).stringType().noDefaultValue().withDescription("");
    public static final ConfigOption<String> ES_MAPPING_VERSION_TYPE_EXTERNAL = ConfigOptions.key(ConfigurationOptions.ES_MAPPING_VERSION_TYPE_EXTERNAL).stringType().noDefaultValue().withDescription("");
    public static final ConfigOption<String> ES_MAPPING_VERSION_TYPE_EXTERNAL_GT = ConfigOptions.key(ConfigurationOptions.ES_MAPPING_VERSION_TYPE_EXTERNAL_GT).stringType().noDefaultValue().withDescription("");
    public static final ConfigOption<String> ES_MAPPING_VERSION_TYPE_EXTERNAL_GTE = ConfigOptions.key(ConfigurationOptions.ES_MAPPING_VERSION_TYPE_EXTERNAL_GTE).stringType().noDefaultValue().withDescription("");
    public static final ConfigOption<String> ES_MAPPING_VERSION_TYPE_FORCE = ConfigOptions.key(ConfigurationOptions.ES_MAPPING_VERSION_TYPE_FORCE).stringType().noDefaultValue().withDescription("");
    public static final ConfigOption<String> ES_READ_FIELD_EMPTY_AS_NULL_LEGACY = ConfigOptions.key(ConfigurationOptions.ES_READ_FIELD_EMPTY_AS_NULL_LEGACY).stringType().noDefaultValue().withDescription("");
    public static final ConfigOption<String> ES_READ_FIELD_VALIDATE_PRESENCE_LEGACY = ConfigOptions.key(ConfigurationOptions.ES_READ_FIELD_VALIDATE_PRESENCE_LEGACY).stringType().noDefaultValue().withDescription("");
    public static final ConfigOption<String> ES_READ_FIELD_INCLUDE = ConfigOptions.key(ConfigurationOptions.ES_READ_FIELD_INCLUDE).stringType().noDefaultValue().withDescription("");
    public static final ConfigOption<String> ES_READ_FIELD_EXCLUDE = ConfigOptions.key(ConfigurationOptions.ES_READ_FIELD_EXCLUDE).stringType().noDefaultValue().withDescription("");
    public static final ConfigOption<String> ES_READ_FIELD_AS_ARRAY_INCLUDE = ConfigOptions.key(ConfigurationOptions.ES_READ_FIELD_AS_ARRAY_INCLUDE).stringType().noDefaultValue().withDescription("");
    public static final ConfigOption<String> ES_READ_FIELD_AS_ARRAY_EXCLUDE = ConfigOptions.key(ConfigurationOptions.ES_READ_FIELD_AS_ARRAY_EXCLUDE).stringType().noDefaultValue().withDescription("");
    public static final ConfigOption<String> ES_READ_SOURCE_FILTER = ConfigOptions.key(ConfigurationOptions.ES_READ_SOURCE_FILTER).stringType().noDefaultValue().withDescription("");
    public static final ConfigOption<String> ES_OPERATION_INDEX = ConfigOptions.key(ConfigurationOptions.ES_OPERATION_INDEX).stringType().noDefaultValue().withDescription("");
    public static final ConfigOption<String> ES_OPERATION_CREATE = ConfigOptions.key(ConfigurationOptions.ES_OPERATION_CREATE).stringType().noDefaultValue().withDescription("");
    public static final ConfigOption<String> ES_OPERATION_UPDATE = ConfigOptions.key(ConfigurationOptions.ES_OPERATION_UPDATE).stringType().noDefaultValue().withDescription("");
    public static final ConfigOption<String> ES_OPERATION_UPSERT = ConfigOptions.key(ConfigurationOptions.ES_OPERATION_UPSERT).stringType().noDefaultValue().withDescription("");
    public static final ConfigOption<String> ES_OPERATION_DELETE = ConfigOptions.key(ConfigurationOptions.ES_OPERATION_DELETE).stringType().noDefaultValue().withDescription("");
    public static final ConfigOption<String> ES_UPDATE_SCRIPT_FILE = ConfigOptions.key(ConfigurationOptions.ES_UPDATE_SCRIPT_FILE).stringType().noDefaultValue().withDescription("");
    public static final ConfigOption<String> ES_UPDATE_SCRIPT_INLINE = ConfigOptions.key(ConfigurationOptions.ES_UPDATE_SCRIPT_INLINE).stringType().noDefaultValue().withDescription("");
    public static final ConfigOption<String> ES_UPDATE_SCRIPT_STORED = ConfigOptions.key(ConfigurationOptions.ES_UPDATE_SCRIPT_STORED).stringType().noDefaultValue().withDescription("");
    public static final ConfigOption<String> ES_UPDATE_SCRIPT_LEGACY = ConfigOptions.key(ConfigurationOptions.ES_UPDATE_SCRIPT_LEGACY).stringType().noDefaultValue().withDescription("");
    public static final ConfigOption<String> ES_UPDATE_SCRIPT_LANG = ConfigOptions.key(ConfigurationOptions.ES_UPDATE_SCRIPT_LANG).stringType().noDefaultValue().withDescription("");
    public static final ConfigOption<String> ES_UPDATE_SCRIPT_PARAMS = ConfigOptions.key(ConfigurationOptions.ES_UPDATE_SCRIPT_PARAMS).stringType().noDefaultValue().withDescription("");
    public static final ConfigOption<String> ES_UPDATE_SCRIPT_PARAMS_JSON = ConfigOptions.key(ConfigurationOptions.ES_UPDATE_SCRIPT_PARAMS_JSON).stringType().noDefaultValue().withDescription("");
    public static final ConfigOption<String> ES_NET_SSL_KEYSTORE_LOCATION = ConfigOptions.key(ConfigurationOptions.ES_NET_SSL_KEYSTORE_LOCATION).stringType().noDefaultValue().withDescription("");
    public static final ConfigOption<String> ES_NET_SSL_KEYSTORE_PASS = ConfigOptions.key(ConfigurationOptions.ES_NET_SSL_KEYSTORE_PASS).stringType().noDefaultValue().withDescription("");
    public static final ConfigOption<String> ES_NET_SSL_TRUST_STORE_LOCATION = ConfigOptions.key(ConfigurationOptions.ES_NET_SSL_TRUST_STORE_LOCATION).stringType().noDefaultValue().withDescription("");
    public static final ConfigOption<String> ES_NET_SSL_TRUST_STORE_PASS = ConfigOptions.key(ConfigurationOptions.ES_NET_SSL_TRUST_STORE_PASS).stringType().noDefaultValue().withDescription("");
    public static final ConfigOption<String> ES_NET_HTTP_HEADER_PREFIX = ConfigOptions.key(ConfigurationOptions.ES_NET_HTTP_HEADER_PREFIX).stringType().noDefaultValue().withDescription("");
    public static final ConfigOption<String> ES_NET_HTTP_AUTH_USER = ConfigOptions.key(ConfigurationOptions.ES_NET_HTTP_AUTH_USER).stringType().noDefaultValue().withDescription("");
    public static final ConfigOption<String> ES_NET_HTTP_AUTH_PASS = ConfigOptions.key(ConfigurationOptions.ES_NET_HTTP_AUTH_PASS).stringType().noDefaultValue().withDescription("");
    public static final ConfigOption<String> ES_NET_SPNEGO_AUTH_ELASTICSEARCH_PRINCIPAL = ConfigOptions.key(ConfigurationOptions.ES_NET_SPNEGO_AUTH_ELASTICSEARCH_PRINCIPAL).stringType().noDefaultValue().withDescription("");
    public static final ConfigOption<String> ES_NET_PROXY_HTTP_HOST = ConfigOptions.key(ConfigurationOptions.ES_NET_PROXY_HTTP_HOST).stringType().noDefaultValue().withDescription("");
    public static final ConfigOption<String> ES_NET_PROXY_HTTP_PORT = ConfigOptions.key(ConfigurationOptions.ES_NET_PROXY_HTTP_PORT).stringType().noDefaultValue().withDescription("");
    public static final ConfigOption<String> ES_NET_PROXY_HTTP_USER = ConfigOptions.key(ConfigurationOptions.ES_NET_PROXY_HTTP_USER).stringType().noDefaultValue().withDescription("");
    public static final ConfigOption<String> ES_NET_PROXY_HTTP_PASS = ConfigOptions.key(ConfigurationOptions.ES_NET_PROXY_HTTP_PASS).stringType().noDefaultValue().withDescription("");
    public static final ConfigOption<String> ES_NET_PROXY_HTTPS_HOST = ConfigOptions.key(ConfigurationOptions.ES_NET_PROXY_HTTPS_HOST).stringType().noDefaultValue().withDescription("");
    public static final ConfigOption<String> ES_NET_PROXY_HTTPS_PORT = ConfigOptions.key(ConfigurationOptions.ES_NET_PROXY_HTTPS_PORT).stringType().noDefaultValue().withDescription("");
    public static final ConfigOption<String> ES_NET_PROXY_HTTPS_USER = ConfigOptions.key(ConfigurationOptions.ES_NET_PROXY_HTTPS_USER).stringType().noDefaultValue().withDescription("");
    public static final ConfigOption<String> ES_NET_PROXY_HTTPS_PASS = ConfigOptions.key(ConfigurationOptions.ES_NET_PROXY_HTTPS_PASS).stringType().noDefaultValue().withDescription("");
    public static final ConfigOption<String> ES_NET_PROXY_SOCKS_HOST = ConfigOptions.key(ConfigurationOptions.ES_NET_PROXY_SOCKS_HOST).stringType().noDefaultValue().withDescription("");
    public static final ConfigOption<String> ES_NET_PROXY_SOCKS_PORT = ConfigOptions.key(ConfigurationOptions.ES_NET_PROXY_SOCKS_PORT).stringType().noDefaultValue().withDescription("");
    public static final ConfigOption<String> ES_NET_PROXY_SOCKS_USER = ConfigOptions.key(ConfigurationOptions.ES_NET_PROXY_SOCKS_USER).stringType().noDefaultValue().withDescription("");
    public static final ConfigOption<String> ES_NET_PROXY_SOCKS_PASS = ConfigOptions.key(ConfigurationOptions.ES_NET_PROXY_SOCKS_PASS).stringType().noDefaultValue().withDescription("");
    public static final ConfigOption<String> ES_SECURITY_AUTHENTICATION = ConfigOptions.key(ConfigurationOptions.ES_SECURITY_AUTHENTICATION).stringType().noDefaultValue().withDescription("");
    public static final ConfigOption<String> ES_SECURITY_USER_PROVIDER_CLASS = ConfigOptions.key(ConfigurationOptions.ES_SECURITY_USER_PROVIDER_CLASS).stringType().noDefaultValue().withDescription("");
    public static final ConfigOption<String> ES_MAPPING_DEFAULT_EXTRACTOR_CLASS = ConfigOptions.key(ConfigurationOptions.ES_MAPPING_DEFAULT_EXTRACTOR_CLASS).stringType().noDefaultValue().withDescription("");

    public static ConfigOption<String>[] ES_CONFIG_OPTIONS = new ConfigOption[]{
            ES_NODES,
            ES_NODES_DISCOVERY,
            ES_PORT,
            ES_NODES_PATH_PREFIX,
            ES_NODES_CLIENT_ONLY,
            ES_NODES_DATA_ONLY,
            ES_NODES_INGEST_ONLY,
            ES_NODES_WAN_ONLY,
            ES_BATCH_SIZE_BYTES,
            ES_BATCH_SIZE_ENTRIES,
            ES_BATCH_FLUSH_MANUAL,
            ES_BATCH_WRITE_REFRESH,
            ES_BATCH_WRITE_RETRY_COUNT,
            ES_BATCH_WRITE_RETRY_LIMIT,
            ES_BATCH_WRITE_RETRY_WAIT,
            ES_BATCH_WRITE_RETRY_POLICY,
            ES_HTTP_TIMEOUT,
            ES_HTTP_RETRIES,
            ES_SCROLL_KEEPALIVE,
            ES_SCROLL_SIZE,
            ES_SCROLL_LIMIT,
            ES_HEART_BEAT_LEAD,
            ES_INPUT_JSON,
            ES_INDEX_AUTO_CREATE,
            ES_INDEX_READ_MISSING_AS_EMPTY,
            ES_INDEX_READ_ALLOW_RED_STATUS,
            ES_READ_SHARD_PREFERENCE,
            ES_MAPPING_CONSTANT_AUTO_QUOTE,
            ES_MAPPING_DATE_RICH_OBJECT,
            ES_MAPPING_INCLUDE,
            ES_MAPPING_EXCLUDE,
            ES_INGEST_PIPELINE,
            ES_SPARK_DATAFRAME_WRITE_NULL_VALUES,
            ES_READ_FIELD_EMPTY_AS_NULL,
            ES_READ_FIELD_VALIDATE_PRESENCE,
            ES_READ_METADATA,
            ES_READ_METADATA_FIELD,
            ES_READ_METADATA_VERSION,
            ES_READ_UNMAPPED_FIELDS_IGNORE,
            ES_WRITE_OPERATION,
            ES_UPDATE_RETRY_ON_CONFLICT,
            ES_OUTPUT_JSON,
            ES_NET_TRANSPORT_POOLING_EXPIRATION_TIMEOUT,
            ES_NET_USE_SSL,
            ES_NET_SSL_PROTOCOL,
            ES_NET_SSL_KEYSTORE_TYPE,
            ES_NET_SSL_CERT_ALLOW_SELF_SIGNED,
            ES_NET_SPNEGO_AUTH_MUTUAL,
            ES_NET_PROXY_HTTP_USE_SYSTEM_PROPS,
            ES_NET_PROXY_HTTPS_USE_SYSTEM_PROPS,
            ES_NET_PROXY_SOCKS_USE_SYSTEM_PROPS,
            ES_MAPPING_DEFAULT_INDEX_EXTRACTOR_CLASS,
            ES_MAPPING_DEFAULT_INDEX_FORMATTER_CLASS,
            ES_MAPPING_PARAMS_DEFAULT_EXTRACTOR_CLASS,
            ES_RESOURCE,
            ES_RESOURCE_READ,
            ES_RESOURCE_WRITE,
            ES_QUERY,
            ES_NODES_RESOLVE_HOST_NAME,
            ES_KEYSTORE_LOCATION,
            ES_BATCH_WRITE_RETRY_POLICY_NONE,
            ES_BATCH_WRITE_RETRY_POLICY_SIMPLE,
            ES_SERIALIZATION_WRITER_VALUE_CLASS,
            ES_SERIALIZATION_WRITER_BYTES_CLASS,
            ES_SERIALIZATION_READER_VALUE_CLASS,
            ES_MAX_DOCS_PER_PARTITION,
            ES_MAPPING_METADATA_EXTRACTOR_CLASS,
            ES_MAPPING_ID,
            ES_MAPPING_ID_EXTRACTOR_CLASS,
            ES_MAPPING_PARENT,
            ES_MAPPING_PARENT_EXTRACTOR_CLASS,
            ES_MAPPING_JOIN,
            ES_MAPPING_JOIN_EXTRACTOR_CLASS,
            ES_MAPPING_VERSION,
            ES_MAPPING_VERSION_EXTRACTOR_CLASS,
            ES_MAPPING_ROUTING,
            ES_MAPPING_ROUTING_EXTRACTOR_CLASS,
            ES_MAPPING_TTL,
            ES_MAPPING_TTL_EXTRACTOR_CLASS,
            ES_MAPPING_TIMESTAMP,
            ES_MAPPING_TIMESTAMP_EXTRACTOR_CLASS,
            ES_MAPPING_INDEX_EXTRACTOR_CLASS,
            ES_MAPPING_INDEX_FORMATTER_CLASS,
            ES_MAPPING_PARAMS_EXTRACTOR_CLASS,
            ES_MAPPING_VERSION_TYPE,
            ES_MAPPING_VERSION_TYPE_INTERNAL,
            ES_MAPPING_VERSION_TYPE_EXTERNAL,
            ES_MAPPING_VERSION_TYPE_EXTERNAL_GT,
            ES_MAPPING_VERSION_TYPE_EXTERNAL_GTE,
            ES_MAPPING_VERSION_TYPE_FORCE,
            ES_READ_FIELD_EMPTY_AS_NULL_LEGACY,
            ES_READ_FIELD_VALIDATE_PRESENCE_LEGACY,
            ES_READ_FIELD_INCLUDE,
            ES_READ_FIELD_EXCLUDE,
            ES_READ_FIELD_AS_ARRAY_INCLUDE,
            ES_READ_FIELD_AS_ARRAY_EXCLUDE,
            ES_READ_SOURCE_FILTER,
            ES_OPERATION_INDEX,
            ES_OPERATION_CREATE,
            ES_OPERATION_UPDATE,
            ES_OPERATION_UPSERT,
            ES_OPERATION_DELETE,
            ES_UPDATE_SCRIPT_FILE,
            ES_UPDATE_SCRIPT_INLINE,
            ES_UPDATE_SCRIPT_STORED,
            ES_UPDATE_SCRIPT_LEGACY,
            ES_UPDATE_SCRIPT_LANG,
            ES_UPDATE_SCRIPT_PARAMS,
            ES_UPDATE_SCRIPT_PARAMS_JSON,
            ES_NET_SSL_KEYSTORE_LOCATION,
            ES_NET_SSL_KEYSTORE_PASS,
            ES_NET_SSL_TRUST_STORE_LOCATION,
            ES_NET_SSL_TRUST_STORE_PASS,
            ES_NET_HTTP_HEADER_PREFIX,
            ES_NET_HTTP_AUTH_USER,
            ES_NET_HTTP_AUTH_PASS,
            ES_NET_SPNEGO_AUTH_ELASTICSEARCH_PRINCIPAL,
            ES_NET_PROXY_HTTP_HOST,
            ES_NET_PROXY_HTTP_PORT,
            ES_NET_PROXY_HTTP_USER,
            ES_NET_PROXY_HTTP_PASS,
            ES_NET_PROXY_HTTPS_HOST,
            ES_NET_PROXY_HTTPS_PORT,
            ES_NET_PROXY_HTTPS_USER,
            ES_NET_PROXY_HTTPS_PASS,
            ES_NET_PROXY_SOCKS_HOST,
            ES_NET_PROXY_SOCKS_PORT,
            ES_NET_PROXY_SOCKS_USER,
            ES_NET_PROXY_SOCKS_PASS,
            ES_SECURITY_AUTHENTICATION,
            ES_SECURITY_USER_PROVIDER_CLASS,
            ES_MAPPING_DEFAULT_EXTRACTOR_CLASS
    };

}
