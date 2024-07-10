package com.huajieyu.es;

import org.apache.http.HttpHost;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.CreateIndexRequest;
import org.elasticsearch.client.indices.CreateIndexResponse;

public class ElasticSearchClient_Create {
    public static void main(String[] args) throws Exception{

        // 创建客户端
        RestHighLevelClient highLevelClient =
                new RestHighLevelClient(RestClient.builder(new HttpHost("localhost", 9200, "http")));

        // 创建索引
        CreateIndexResponse user = highLevelClient.indices().create(new CreateIndexRequest("user"), RequestOptions.DEFAULT);

        // 响应状态
        System.out.println("响应状态:" + user.isAcknowledged());

        // 关闭客户端
        highLevelClient.close();
    }
}
