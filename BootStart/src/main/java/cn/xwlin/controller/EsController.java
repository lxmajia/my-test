package cn.xwlin.controller;

import cn.xwlin.object.EsOrderInfoIndexOBJ;
import cn.xwlin.util.OrderUtil;
import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.support.WriteRequest;
import org.elasticsearch.client.IndicesClient;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.core.MainResponse;
import org.elasticsearch.client.indices.CreateIndexRequest;
import org.elasticsearch.client.indices.CreateIndexResponse;
import org.elasticsearch.client.indices.GetIndexRequest;
import org.elasticsearch.client.indices.GetIndexResponse;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.core.TimeValue;
import org.elasticsearch.index.query.MatchAllQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.rest.RestStatus;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.sort.SortOrder;
import org.elasticsearch.xcontent.XContentType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.Date;
import java.util.Map;

@RestController
@RequestMapping("es")
public class EsController {

  @Autowired
  private RestHighLevelClient restHighLevelClient;

  @RequestMapping("info")
  public MainResponse info(String name) throws Exception {
    IndicesClient indices = restHighLevelClient.indices();
    MainResponse info = restHighLevelClient.info(RequestOptions.DEFAULT);
    return info;
  }

  @RequestMapping("mapping")
  public GetIndexResponse mapping(String indexName) throws Exception {
    IndicesClient indices = restHighLevelClient.indices();
    GetIndexRequest req = new GetIndexRequest(indexName);
    GetIndexResponse getIndexResponse = indices.get(req, RequestOptions.DEFAULT);
    return getIndexResponse;
  }

  @RequestMapping("createIndex")
  public CreateIndexResponse createIndex() throws Exception {
    IndicesClient indices = restHighLevelClient.indices();
    CreateIndexRequest request = new CreateIndexRequest("goods");
    request.settings(Settings.builder().put("index.number_of_shards", 3).put("index" + ".number_of_replicas", 1).build());

    String mappingStr = "{\"properties\":{\"name\":{\"type\":\"text\",\"fields\":{\"keyword\":{\"type\":\"keyword\"}}},\"sex\":{\"type\":\"keyword\"},\"age\":{\"type\":\"integer\"}}}";
    Map mappings = JSONObject.parseObject(mappingStr, Map.class);
    request.mapping(mappings);
    return indices.create(request, RequestOptions.DEFAULT);
  }

  @RequestMapping("createData")
  public String createData(Long startOrderId, Integer count) throws Exception {
    SearchRequest searchRequest = new SearchRequest();
    searchRequest.indices("order_info");//指定要查询的索引

    SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
    MatchAllQueryBuilder matchAllQueryBuilder = QueryBuilders.matchAllQuery();
    searchSourceBuilder.query(matchAllQueryBuilder);
    searchSourceBuilder.sort("orderId", SortOrder.DESC);
    searchSourceBuilder.size(1);
    //3.将 SearchSourceBuilder 添加到 SearchRequest中
    searchRequest.source(searchSourceBuilder);
    //4.执行查询
    SearchResponse searchResponse = restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);
    //5.解析查询结果
    System.out.println(searchResponse);
    System.out.println("花费的时长：" + searchResponse.getTook());

    SearchHits hits = searchResponse.getHits();
    if (hits.getHits().length > 0) {
      SearchHit hit = hits.getHits()[0];
      long l = Long.parseLong(hit.getId());
      startOrderId = l + 1;
    }

    if (startOrderId < 1000000000L) {
      startOrderId = 1000000000L;
    }

    int successCount = 0;
    for (int i = 0; i < count; i++) {
      EsOrderInfoIndexOBJ esOrderInfoIndex = buildRandomEsObj(startOrderId);
      IndexRequest indexRequest = new IndexRequest("order_info");
      indexRequest.source(JSON.toJSONString(esOrderInfoIndex), XContentType.JSON);
      indexRequest.timeout(TimeValue.timeValueSeconds(1));
      indexRequest.setRefreshPolicy(WriteRequest.RefreshPolicy.WAIT_UNTIL);
      indexRequest.create(true);
      indexRequest.id(esOrderInfoIndex.getOrderId().toString());
      try {
        IndexResponse index = restHighLevelClient.index(indexRequest, RequestOptions.DEFAULT);
        if (index.status().equals(RestStatus.OK)) {
          successCount++;
        }
      } catch (Throwable t) {
      } finally {
        startOrderId++;
      }
    }
    return "SUCCESS：" + successCount + ",maxOrderId:" + startOrderId;
  }

  private EsOrderInfoIndexOBJ buildRandomEsObj(Long orderId) {
    EsOrderInfoIndexOBJ obj = new EsOrderInfoIndexOBJ();
    obj.setOrderId(orderId);
    obj.setOrderStatus(OrderUtil.getOrderStatus());
    obj.setUserId(OrderUtil.getUserId());
    obj.setCreateTime(new Date());
    obj.setContactName(OrderUtil.getName());
    obj.setContactPhone(OrderUtil.getAPhoneNum());
    obj.setGoodsList(OrderUtil.getGoodsInfo());
    return obj;
  }
}