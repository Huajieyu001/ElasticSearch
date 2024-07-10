package com.huajieyu.es.batch;

import com.huajieyu.es.User;
import org.apache.http.HttpHost;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;

public class ElasticSearchClient_DocDeleteBatch {

    public static void main(String[] args) throws Exception{

        RestHighLevelClient highLevelClient =
                new RestHighLevelClient(RestClient.builder(new HttpHost("localhost", 9200, "http")));

        User user = new User(24, "Tom", "6485sdfg@qq.com");

        BulkRequest request = new BulkRequest();

        // 批量删除
        request.add(new DeleteRequest().index("user").id("1001"));
        request.add(new DeleteRequest().index("user").id("1002"));
        request.add(new DeleteRequest().index("user").id("1004"));

        BulkResponse response = highLevelClient.bulk(request, RequestOptions.DEFAULT);

        System.out.println(response);
        System.out.println(response.getTook());
        System.out.println(response.getItems());

        highLevelClient.close();
    }
}
