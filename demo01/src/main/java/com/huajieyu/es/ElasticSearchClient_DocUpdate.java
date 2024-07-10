package com.huajieyu.es;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpHost;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;

public class ElasticSearchClient_DocUpdate {

    public static void main(String[] args) throws Exception{

        RestHighLevelClient highLevelClient =
                new RestHighLevelClient(RestClient.builder(new HttpHost("localhost", 9200, "http")));

        User user = new User(30, "Jack", "5500@qq.com");

        UpdateRequest request = new UpdateRequest();

        request.index("user").id("1001");
        ObjectMapper mapper = new ObjectMapper();
        String uesrJson = mapper.writeValueAsString(user);

        // 方式1
//        request.doc(uesrJson, XContentType.JSON);

        // 方式2
        request.doc(XContentType.JSON, "name", "Rosy");
        // ....
        UpdateResponse response = highLevelClient.update(request, RequestOptions.DEFAULT);

        System.out.println(response);
        highLevelClient.close();
    }
}
