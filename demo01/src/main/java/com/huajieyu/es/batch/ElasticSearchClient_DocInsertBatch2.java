package com.huajieyu.es.batch;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.huajieyu.es.User;
import org.apache.http.HttpHost;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;

public class ElasticSearchClient_DocInsertBatch {

    public static void main(String[] args) throws Exception{

        RestHighLevelClient highLevelClient =
                new RestHighLevelClient(RestClient.builder(new HttpHost("localhost", 9200, "http")));

        // 批量插入数据，如果不存在则为插入，如果已存在则为更新
        BulkRequest request = new BulkRequest();
        request.add(new IndexRequest().index("user").id("1001").source(XContentType.JSON, "name", "1111"));
        request.add(new IndexRequest().index("user").id("1002").source(XContentType.JSON, "name", "2222"));
        request.add(new IndexRequest().index("user").id("1003").source(XContentType.JSON, "name", "wangwu"));
        request.add(new IndexRequest().index("user").id("1004").source(XContentType.JSON, "name", "zhaoliu"));

        BulkResponse bulk = highLevelClient.bulk(request, RequestOptions.DEFAULT);

        System.out.println(bulk);

        highLevelClient.close();
    }
}
