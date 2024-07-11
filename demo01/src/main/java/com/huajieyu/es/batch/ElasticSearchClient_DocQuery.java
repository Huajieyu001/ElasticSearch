package com.huajieyu.es.batch;

import org.apache.http.HttpHost;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.unit.Fuzziness;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.*;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.sort.SortOrder;

public class ElasticSearchClient_DocQuery {

    public static void main(String[] args) throws Exception {

        RestHighLevelClient highLevelClient =
                new RestHighLevelClient(RestClient.builder(new HttpHost("localhost", 9200, "http")));

        SearchRequest request = new SearchRequest();

        // 指定索引
        request.indices("user");

        // 1.无条件则查询user索引下的所有数据
        SearchResponse response = highLevelClient.search(request, RequestOptions.DEFAULT);

        System.out.println(response);
        // 查看访问时间
        System.out.println(response.getTook());

        System.out.println("-----------------------------------------------");

        SearchSourceBuilder builder = new SearchSourceBuilder();

        // 无条件则查询user索引下的所有数据
        request.indices("user").source(builder);
        response = highLevelClient.search(request, RequestOptions.DEFAULT);
        System.out.println(response);
        System.out.println(response.getTook());
        System.out.println("foreach start:");
        response.getHits().forEach(System.out::println);

        // 2.条件查询
        System.out.println("------------------------------2.条件查询");
        TermQueryBuilder ageQuery = QueryBuilders.termQuery("sex", "男");
        request.indices("user").source(new SearchSourceBuilder().query(ageQuery));
        response = highLevelClient.search(request, RequestOptions.DEFAULT);
        System.out.println(response.getTook());
        response.getHits().forEach(System.out::println);

        // 3.分页查询
        System.out.println("------------------------------3.分页查询");
        SearchSourceBuilder builder1 = new SearchSourceBuilder();
        builder1.query(QueryBuilders.matchAllQuery());
        // 从第2条开始查询（条数下标是从0开始的，类似数组）
        builder1.from(2);
        // 指定查询的件数为3
        builder1.size(3);
        request.indices("user").source(builder1);
        response = highLevelClient.search(request, RequestOptions.DEFAULT);
        response.getHits().forEach(System.out::println);

        // 4.查询排序
        System.out.println("------------------------------4.查询排序");
        SearchSourceBuilder builder2 = new SearchSourceBuilder();
        // 按age字段升序排列，升序可省略，默认为升序ASC
        builder2.sort("age", SortOrder.ASC);
        request.indices("user").source(builder2);
        response = highLevelClient.search(request, RequestOptions.DEFAULT);
        System.out.println(response.getTook());
        response.getHits().forEach(System.out::println);

        // 5.过滤字段
        System.out.println("------------------------------5.过滤字段");
        SearchSourceBuilder builder3 = new SearchSourceBuilder();
        // 保留name，排除age字段。sex字段没写，但是因为includes只包含name，所以sex默认排除，
        // 如果includes长度为0，则排除的字段之外的所有字段都会显示
        String[] includes = new String[]{};
        String[] excludes = new String[]{"age"};
        builder3.fetchSource(includes, excludes);
        request.indices("user").source(builder3);
        response = highLevelClient.search(request, RequestOptions.DEFAULT);
        response.getHits().forEach(System.out::println);

        // 6.组合查询
        System.out.println("------------------------------6.组合查询");
        SearchSourceBuilder builder4 = new SearchSourceBuilder();
        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
        boolQueryBuilder.must(QueryBuilders.matchQuery("age", 45));
        boolQueryBuilder.must(QueryBuilders.matchQuery("sex", "男"));
        builder4.query(boolQueryBuilder);
        request.indices("user").source(builder4);
        response = highLevelClient.search(request, RequestOptions.DEFAULT);
        response.getHits().forEach(System.out::println);

        // 7.范围查询
        System.out.println("------------------------------7.范围查询");
        SearchSourceBuilder builder5 = new SearchSourceBuilder();
        // 查询age小于等于30的数据
        RangeQueryBuilder queryBuilder = QueryBuilders.rangeQuery("age").lte(30);
        builder5.query(queryBuilder);
        request.source(builder5);
        response = highLevelClient.search(request, RequestOptions.DEFAULT);
        response.getHits().forEach(System.out::println);

        // 8.模糊查询
        System.out.println("------------------------------8.模糊查询");
        SearchSourceBuilder builder6 = new SearchSourceBuilder();
        // 查询name类似asd的数据，允许有2个字符误差
        FuzzyQueryBuilder fuzziedQuery = QueryBuilders.fuzzyQuery("name", "asd").fuzziness(Fuzziness.TWO);
        builder6.query(fuzziedQuery);
        request.source(builder6);
        response =  highLevelClient.search(request, RequestOptions.DEFAULT);

        response.getHits().forEach(System.out::println);

        highLevelClient.close();
    }
}
