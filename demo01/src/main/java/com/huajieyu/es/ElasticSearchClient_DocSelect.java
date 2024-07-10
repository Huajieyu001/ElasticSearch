package com.huajieyu.es;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpHost;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;

public class ElasticSearchClient_DocSelect {

    public static void main(String[] args) throws Exception{

        RestHighLevelClient highLevelClient =
                new RestHighLevelClient(RestClient.builder(new HttpHost("localhost", 9200, "http")));

        User user = new User(24, "Tom", "6485sdfg@qq.com");

        GetRequest request = new GetRequest();

        request.index("user").id("1001");

        GetResponse response = highLevelClient.get(request, RequestOptions.DEFAULT);

        System.out.println(response);
        System.out.println(response.getSourceAsString());

        highLevelClient.close();
    }
}
