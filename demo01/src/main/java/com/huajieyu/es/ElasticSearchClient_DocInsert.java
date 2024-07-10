package com.huajieyu.es;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpHost;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;

public class ElasticSearchClient_DocInsert {

    public static void main(String[] args) throws Exception{

        RestHighLevelClient highLevelClient =
                new RestHighLevelClient(RestClient.builder(new HttpHost("localhost", 9200, "http")));

        User user = new User(24, "Tom", "6485sdfg@qq.com");

        IndexRequest indexRequest = new IndexRequest();

        indexRequest.index("user").id("1001");

        ObjectMapper mapper = new ObjectMapper();
        String userJson = mapper.writeValueAsString(user);
        indexRequest.source(userJson, XContentType.JSON);

        IndexResponse response = highLevelClient.index(indexRequest, RequestOptions.DEFAULT);

//        System.out.println(response.getIndex().);
        highLevelClient.close();
    }
}
