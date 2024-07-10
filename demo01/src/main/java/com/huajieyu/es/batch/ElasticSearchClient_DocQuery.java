package com.huajieyu.es.batch;

import org.apache.http.HttpHost;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;

public class ElasticSearchClient_DocInsertBatch2 {

    public static void main(String[] args) throws Exception{

        RestHighLevelClient highLevelClient =
                new RestHighLevelClient(RestClient.builder(new HttpHost("localhost", 9200, "http")));

        // 批量插入数据，如果不存在则为插入，如果已存在则为更新
        BulkRequest request = new BulkRequest();
        request.add(new IndexRequest().index("user").id("1001").source(XContentType.JSON, "name", "asdfg", "age", 45, "sex", "男"));
        request.add(new IndexRequest().index("user").id("1002").source(XContentType.JSON, "name", "asdfg", "age", 32, "sex", "男"));
        request.add(new IndexRequest().index("user").id("1003").source(XContentType.JSON, "name", "asdfg", "age", 42, "sex", "女"));
        request.add(new IndexRequest().index("user").id("1004").source(XContentType.JSON, "name", "asdfg", "age", 21, "sex", "男"));
        request.add(new IndexRequest().index("user").id("1005").source(XContentType.JSON, "name", "asdfg", "age", 54, "sex", "女"));
        request.add(new IndexRequest().index("user").id("1006").source(XContentType.JSON, "name", "asdfg", "age", 24, "sex", "女"));
        request.add(new IndexRequest().index("user").id("1007").source(XContentType.JSON, "name", "asdfg", "age", 45, "sex", "男"));

        BulkResponse bulk = highLevelClient.bulk(request, RequestOptions.DEFAULT);

        System.out.println(bulk);

        highLevelClient.close();
    }
}
