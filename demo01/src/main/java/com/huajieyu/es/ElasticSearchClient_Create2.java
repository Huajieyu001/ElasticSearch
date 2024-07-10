package com.huajieyu.es;

import org.apache.http.HttpHost;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.CreateIndexRequest;
import org.elasticsearch.client.indices.CreateIndexResponse;

public class ElasticSearchClient_Create2 {
    public static void main(String[] args) throws Exception{
        RestHighLevelClient client =
                new RestHighLevelClient(RestClient.builder(new HttpHost("127.0.0.1", 9200, "http")));

        CreateIndexRequest request = new CreateIndexRequest("user");
        CreateIndexResponse response = client.indices().create(request, RequestOptions.DEFAULT);
        System.out.println(response.isAcknowledged());

        client.close();
    }
}
