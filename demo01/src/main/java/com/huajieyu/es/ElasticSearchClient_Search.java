package com.huajieyu.es;

import org.apache.http.HttpHost;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.GetIndexRequest;
import org.elasticsearch.client.indices.GetIndexResponse;

public class ElasticSearchClient_Search {
    public static void main(String[] args) throws Exception{

        // 创建客户端
        RestHighLevelClient highLevelClient =
                new RestHighLevelClient(RestClient.builder(new HttpHost("localhost", 9200, "http")));

        // 创建索引
        GetIndexResponse user = highLevelClient.indices().get(new GetIndexRequest("user"), RequestOptions.DEFAULT);

        // 响应状态
        System.out.println("Data1:" + user.getAliases());
        System.out.println("Data2:" + user.getMappings());
        System.out.println("Data3:" + user.getSettings());

        // 关闭客户端
        highLevelClient.close();
    }
}
