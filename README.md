# flink-connector-elasticsearch-source

## Why

[Elasticsearch | Apache Flink](https://ci.apache.org/projects/flink/flink-docs-release-1.13/docs/connectors/datastream/elasticsearch/) / [Elasticsearch | Apache Flink](https://ci.apache.org/projects/flink/flink-docs-release-1.13/docs/connectors/table/elasticsearch/) / [flink-connector-elasticsearch7](https://github.com/apache/flink/tree/release-1.13.1/flink-connectors/flink-connector-elasticsearch7) doesn't support Elasticsearch Source and Table

## How

* [Hadoop Compatibility | Apache Flink](https://ci.apache.org/projects/flink/flink-docs-release-1.13/docs/dev/dataset/hadoop_compatibility/)

* [elasticsearch-hadoop](https://github.com/elastic/elasticsearch-hadoop) / [ Elasticsearch for Apache Hadoop](https://www.elastic.co/guide/en/elasticsearch/hadoop/7.x/mapreduce.html)

## Install

```
mvn package -Dmaven.test.skip=true
cp target/target/flink-connector-elasticsearch-hadoop-1.0.jar /opt/flink/lib/
```

## Use

```sql
CREATE TABLE flink_es_table(
    _metadata ROW<_index STRING,_type STRING,_id STRING>
    ) WITH (
        'connector.type'='elasticsearch',
        'es.resource'='flink_es_table/_doc',
        'es.nodes'='127.0.0.1:9200',
        'es.port'='9200',
        'es.query'='?q=*',
        'es.nodes.client.only'='false',
        'es.nodes.discovery'='false',
        'es.nodes.wan.only'='true'
);

SELECT _index,_type,_id FROM flink_es_table;
```

## Detail

[Elasticsearch for Apache Hadoop](https://www.elastic.co/guide/en/elasticsearch/hadoop/7.x/mapreduce.html)

[Configuration](https://www.elastic.co/guide/en/elasticsearch/hadoop/7.x/configuration.html)

## Test

### init

`docker run -d --name es7 -p 9200:9200 -p 9300:9300 -e "discovery.type=single-node" docker.elastic.co/elasticsearch/elasticsearch:7.10.2-amd64`

`curl -XPOST --header "Content-Type: application/json" "http://127.0.0.1:9200/_bulk" --data-binary @data/flink_es_table`

```json
{"took":640,"errors":false,"items":[{"index":{"_index":"flink_es_table","_type":"_doc","_id":"es_id_1","_version":1,"result":"created","_shards":{"total":2,"successful":1,"failed":0},"_seq_no":0,"_primary_term":1,"status":201}},{"create":{"_index":"flink_es_table","_type":"_doc","_id":"es_id_2","_version":1,"result":"created","_shards":{"total":2,"successful":1,"failed":0},"_seq_no":1,"_primary_term":1,"status":201}}]}
```

### run

`org.apache.flink.connector.elasticsearch.table.ElasticsearchTableSourceTest`

output

```sql
CREATE TABLE flink_es_table(_metadata ROW<_index STRING,_type STRING,_id STRING>,int_key INT,int_array ARRAY<INT>,int_object MAP<STRING,INT>,int_nested ARRAY<ROW<key_3 INT,key_4 INT>>,string_key STRING,string_array ARRAY<STRING>,string_object MAP<STRING,STRING>,string_nested ARRAY<ROW<key_3 STRING,key_4 STRING>>,double_key DOUBLE,double_array ARRAY<DOUBLE>,double_object MAP<STRING,DOUBLE>,double_nested ARRAY<ROW<key_3 DOUBLE,key_4 DOUBLE>>,time_key TIMESTAMP,time_array ARRAY<TIMESTAMP>,time_object MAP<STRING,TIMESTAMP>,time_nested ARRAY<ROW<key_3 TIMESTAMP,key_4 TIMESTAMP>>,bool_key BOOLEAN,bool_array ARRAY<BOOLEAN>,bool_object MAP<STRING,BOOLEAN>,bool_nested ARRAY<ROW<key_3 BOOLEAN,key_4 BOOLEAN>>) WITH (  'connector.type'='elasticsearch',  'es.resource'='flink_es_table/_doc',  'es.nodes'='k8s.cuidp.top:9201',  'es.port'='9201',  'es.query'='?q=*',  'es.nodes.client.only'='false',  'es.nodes.discovery'='false',  'es.nodes.wan.only'='true')

SELECT _index,_type,_id,int_key,int_array,int_object,int_nested,int_key,int_array,int_object,int_nested,string_key,string_array,string_object,string_nested,time_key,time_array,time_object,time_nested,bool_key,bool_array,bool_object,bool_nested FROM flink_es_table

input results:
+I[flink_es_table, _doc, es_id_1, 10, [11, 12], {key_2=14, key_1=13}, [+I[15, 16], +I[17, 18]], 10, [11, 12], {key_2=14, key_1=13}, [+I[15, 16], +I[17, 18]], str0, [str1, str2], {key_2=str4, key_1=str3}, [+I[str5, str6], +I[str7, str8]], 2021-01-10 00:00:00.0, [2021-01-11T00:00, 2021-01-12T00:00], {key_2=2021-01-14 00:00:00.0, key_1=2021-01-13 00:00:00.0}, [+I[2021-01-15 00:00:00.0, 2021-01-16 00:00:00.0], +I[2021-01-17 00:00:00.0, 2021-01-18 00:00:00.0]], true, [true, false], {key_2=false, key_1=true}, [+I[true, false], +I[false, true]]]
+I[flink_es_table, _doc, es_id_2, 20, [21, 22], {key_2=24, key_1=23}, [+I[25, 26], +I[27, 28]], 20, [21, 22], {key_2=24, key_1=23}, [+I[25, 26], +I[27, 28]], str0, [str1, str2], {key_2=str4, key_1=str3}, [+I[str5, str6], +I[str7, str8]], 2021-01-20 00:00:00.0, [2021-01-21T00:00, 2021-01-22T00:00], {key_2=2021-01-24 00:00:00.0, key_1=2021-01-23 00:00:00.0}, [+I[2021-01-25 00:00:00.0, 2021-01-26 00:00:00.0], +I[2021-01-27 00:00:00.0, 2021-01-28 00:00:00.0]], true, [true, false], {key_2=false, key_1=true}, [+I[true, false], +I[false, true]]]

```


## Read And Write(just for example)

[Remote clusters](https://www.elastic.co/guide/en/elasticsearch/reference/7.x/modules-remote-clusters.html) and [Reindex API](https://www.elastic.co/guide/en/elasticsearch/reference/7.x/docs-reindex.html#reindex-from-remote)

` ./bin/sql-client.sh`

### create elasticsearch source table

```sql
CREATE TABLE flink_es_table(
    _metadata ROW<_index STRING,_type STRING,_id STRING>,
    int_key INT,int_array ARRAY<INT>,int_object MAP<STRING,INT>,int_nested ARRAY<ROW<key_3 INT,key_4 INT>>,
    string_key STRING,string_array ARRAY<STRING>,string_object MAP<STRING,STRING>,string_nested ARRAY<ROW<key_3 STRING,key_4 STRING>>,
    double_key DOUBLE,double_array ARRAY<DOUBLE>,double_object MAP<STRING,DOUBLE>,double_nested ARRAY<ROW<key_3 DOUBLE,key_4 DOUBLE>>,
    time_key TIMESTAMP,time_array ARRAY<TIMESTAMP>,time_object MAP<STRING,TIMESTAMP>,time_nested ARRAY<ROW<key_3 TIMESTAMP,key_4 TIMESTAMP>>,
    bool_key BOOLEAN,bool_array ARRAY<BOOLEAN>,bool_object MAP<STRING,BOOLEAN>,bool_nested ARRAY<ROW<key_3 BOOLEAN,key_4 BOOLEAN>>
    ) WITH (
        'connector.type'='elasticsearch',
        'es.resource'='flink_es_table/_doc',
        'es.nodes'='127.0.0.1:9200',
        'es.port'='9200',
        'es.query'='?q=*',
        'es.nodes.client.only'='false',
        'es.nodes.discovery'='false',
        'es.nodes.wan.only'='true'
    );
```



### create elasticsearch sink table

* orginal

  es doc source with id

  ```sql
  CREATE TABLE flink_es_table_copy_sink(
      id STRING,
      int_key INT,int_array ARRAY<INT>,int_object MAP<STRING,INT>,int_nested ARRAY<ROW<key_3 INT,key_4 INT>>,
      PRIMARY KEY (`id`) NOT ENFORCED) WITH ( 
          'connector'='elasticsearch-7', 
          'index'='flink_es_table_copy', 
          'hosts'='127.0.0.1:9200'
  );
  ```

  

* custom

  es doc source without id

  ```sql
  CREATE TABLE flink_es_table_copy_sink(
      id STRING,
      int_key INT,int_array ARRAY<INT>,int_object MAP<STRING,INT>,int_nested ARRAY<ROW<key_3 INT,key_4 INT>>,
      PRIMARY KEY (`id`) NOT ENFORCED) WITH ( 
          'connector'='elasticsearch-7-ignore', 
          'index'='flink_es_table_copy', 
          'hosts'='127.0.0.1:9200',
          'ignore-fields'='id'
  );
  ```

### source->sink

```sql
insert into flink_es_table_copy_sink SELECT _id,int_key,int_array,int_object,int_nested FROM flink_es_table; 
```



#### search copy

```sql
CREATE TABLE flink_es_table_copy_source(
    _metadata ROW<_index STRING,_type STRING,_id STRING>,
    int_key INT,int_array ARRAY<INT>,int_object MAP<STRING,INT>,int_nested ARRAY<ROW<key_3 INT,key_4 INT>>
    ) WITH (
        'connector.type'='elasticsearch',
        'es.resource'='flink_es_table_copy/_doc',
        'es.nodes'='127.0.0.1:9200',
        'es.port'='9200',
        'es.query'='?q=*',
        'es.nodes.client.only'='false',
        'es.nodes.discovery'='false',
        'es.nodes.wan.only'='true'
    );
    
select * from flink_es_table_copy_source;
```

#### result

```shell
                            SQL Query Result (Table)
 Table program finished.        Page: Last of 1           Updated: 11:25:32.615

                      _metadata                        int_key
 +I[flink_es_table_copy, _doc,~                             10
 +I[flink_es_table_copy, _doc,~                             20
Q Quit         + Inc Refresh  G Goto Page    N Next Page    O Open Row
R Refresh      - Dec Refresh  L Last Page    P Prev Page

---
 _metadata (ROW<`_index` STRING, `_type` STRING, `_id` STRING>):
 +I[flink_es_table_copy, _doc, es_id_1]
 int_key (INT):
 10
 int_array (ARRAY<INT>):
 [11, 12]
 int_object (MAP<STRING, INT>):
 {key_2=14, key_1=13}
 int_nested (ARRAY<ROW<`key_3` INT, `key_4` INT>>):
 [+I[15, 16], +I[17, 18]]
```

## More

OutputRowFunction`+`DeserializationSchema<RowData>` should load other Hadoop InputFormats as Table

