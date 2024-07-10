package com.huajieyu.es;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpHost;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;

public class ElasticSearchClient_DocDelete {

    public static void main(String[] args) throws Exception{

        RestHighLevelClient highLevelClient =
                new RestHighLevelClient(RestClient.builder(new HttpHost("localhost", 9200, "http")));

        User user = new User(24, "Tom", "6485sdfg@qq.com");

        DeleteRequest request = new DeleteRequest();

        request.index("user").id("1001");

        DeleteResponse response = highLevelClient.delete(request, RequestOptions.DEFAULT);

        System.out.println(response);
        highLevelClient.close();
    }
}
