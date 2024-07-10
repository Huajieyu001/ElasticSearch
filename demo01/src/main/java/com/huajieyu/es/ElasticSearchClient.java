package com.huajieyu.es;

import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.transport.Transport;

public class ElasticSearchClient {
    public static void main(String[] args) throws Exception{

        // 创建客户端
        RestHighLevelClient highLevelClient =
                new RestHighLevelClient(RestClient.builder(new HttpHost("localhost", 9200, "http")));
        // 关闭客户端
        highLevelClient.close();


    }
}
