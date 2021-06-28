package org.apache.flink.connector.elasticsearch.table;

import org.apache.flink.api.java.ExecutionEnvironment;
import org.apache.flink.table.api.*;
import org.apache.flink.table.api.bridge.java.BatchTableEnvironment;
import org.apache.flink.test.util.AbstractTestBase;
import org.apache.flink.types.Row;
import org.junit.Before;
import org.junit.Test;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class ElasticsearchTableSourceTest extends AbstractTestBase {

    public static final String INPUT_TABLE = "flink_es_table";
    ExecutionEnvironment env;
    BatchTableEnvironment tEnv;

    private static List<String> manifestResults(TableResult result) {
        Iterator<Row> resultIterator = result.collect();
        return StreamSupport.stream(
                Spliterators.spliteratorUnknownSize(resultIterator, Spliterator.ORDERED),
                false)
                .map(Row::toString)
                .collect(Collectors.toList());
    }

    @Before
    public void before() {
        env = ExecutionEnvironment.getExecutionEnvironment();
        tEnv = BatchTableEnvironment.create(env);
        String esResource = INPUT_TABLE + "/_doc";
        String esNodes="127.0.0.1:9200";
        String esPort="9200";
        String esQuery = "?q=*";
        String clientOnly = "false";
        String discovery = "false";
        String wanOnly = "true";

        String inputRowStr = "(" +
                "_metadata ROW<_index STRING,_type STRING,_id STRING>,"
//                "_metadata MAP<STRING,STRING>,\n"
                + "int_key INT,"
                + "int_array ARRAY<INT>,"
                + "int_object MAP<STRING,INT>,"
                + "int_nested ARRAY<ROW<key_3 INT,key_4 INT>>"
                + ","

                + "string_key STRING,"
                + "string_array ARRAY<STRING>,"
                + "string_object MAP<STRING,STRING>,"
                + "string_nested ARRAY<ROW<key_3 STRING,key_4 STRING>>"
                + ","

                + "double_key DOUBLE,"
                + "double_array ARRAY<DOUBLE>,"
                + "double_object MAP<STRING,DOUBLE>,"
                + "double_nested ARRAY<ROW<key_3 DOUBLE,key_4 DOUBLE>>"
                + ","

                + "time_key TIMESTAMP,"
                + "time_array ARRAY<TIMESTAMP>,"
                + "time_object MAP<STRING,TIMESTAMP>,"
                + "time_nested ARRAY<ROW<key_3 TIMESTAMP,key_4 TIMESTAMP>>"
                + ","

                + "bool_key BOOLEAN,"
                + "bool_array ARRAY<BOOLEAN>,"
                + "bool_object MAP<STRING,BOOLEAN>,"
                + "bool_nested ARRAY<ROW<key_3 BOOLEAN,key_4 BOOLEAN>>"
                + ")";


        String createInputTable = "CREATE TABLE "
                + INPUT_TABLE
                + inputRowStr
                + " WITH ("
                + "  'connector.type'='elasticsearch',"
                + "  'es.resource'='" + esResource + "',"
                + "  'es.nodes'='" + esNodes + "',"
                + "  'es.port'='" + esPort + "',"
                + "  'es.query'='" + esQuery + "',"
                + "  'es.nodes.client.only'='" + clientOnly + "',"
                + "  'es.nodes.discovery'='" + discovery + "',"
                + "  'es.nodes.wan.only'='" + wanOnly + "'"
                + ")";
        System.out.println(createInputTable);
        tEnv.executeSql(createInputTable);
    }

    @Test
    public void testEsSource() {
        String selectColumes = "SELECT _index,_type,_id," +
//        String selectColumes="SELECT _metadata['_index'],_metadata['_type'],_metadata['_id'],\n" +
                "int_key,int_array,int_object,int_nested," +
                "int_key,int_array,int_object,int_nested," +
                "string_key,string_array,string_object,string_nested," +
                "time_key,time_array,time_object,time_nested," +
                "bool_key,bool_array,bool_object,bool_nested FROM " + INPUT_TABLE;
        String selectSql = selectColumes;
        System.out.println(selectSql);
        TableResult tableResult = tEnv.executeSql(selectSql);
        List<String> results = manifestResults(tableResult);
        results.forEach(System.out::println);
    }
}