package cn.xwlin.controller;

import cn.hutool.core.util.RandomUtil;
import cn.xwlin.object.EsOrderInfoIndexOBJ;
import cn.xwlin.server.EsServer;
import cn.xwlin.util.OrderUtil;
import cn.xwlin.vo.EsQueryResult;
import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
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
import org.elasticsearch.common.Strings;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.core.TimeValue;
import org.elasticsearch.index.query.MatchAllQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.TermQueryBuilder;
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

import java.util.*;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

@RestController
@RequestMapping("es")
public class EsController {

  @Autowired
  private RestHighLevelClient restHighLevelClient;
  @Autowired
  private EsServer esServer;

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
  public String createData(Integer count) throws Exception {
    String indexName = "order-info";

    Long startOrderId = 0L;
    SearchRequest searchRequest = new SearchRequest();
    searchRequest.indices(indexName);//指定要查询的索引

    MatchAllQueryBuilder matchAllQueryBuilder = QueryBuilders.matchAllQuery();
    // 设置查询的字段
    String[] includes = new String[]{"orderId", "contactName", "contactPhone", "goodsList", "createTime"}; // 替换为你想查询的字段
    String[] excludes = Strings.EMPTY_ARRAY; // 不排除任何字段

    SearchSourceBuilder queryBuilder = new SearchSourceBuilder();
    queryBuilder.query(matchAllQueryBuilder);
    queryBuilder.fetchSource(includes, excludes);
    queryBuilder.sort("orderId", SortOrder.DESC);
    queryBuilder.size(2);

    EsQueryResult<EsOrderInfoIndexOBJ> esOrderInfoIndexOBJEsQueryResult = esServer.queryByTemplate(queryBuilder, EsOrderInfoIndexOBJ.class, indexName);

    if (esOrderInfoIndexOBJEsQueryResult.getCount() > 0) {
      long l = esOrderInfoIndexOBJEsQueryResult.getList().get(0).getOrderId();
      startOrderId = l + 1;
    }

    if (startOrderId < 1000000000L) {
      startOrderId = 1000000000L;
    }

    int successCount = 0;

    List<IndexRequest> requestList = new ArrayList<>();
    for (int i = 0; i < count; i++) {
      EsOrderInfoIndexOBJ esOrderInfoIndex = null;
      int i1 = RandomUtil.randomInt(1, 100);
      if (i1 < 4) {
        esOrderInfoIndex = buildMyRandomEsObj(startOrderId);
      } else {
        esOrderInfoIndex = buildRandomEsObj(startOrderId);
      }
      IndexRequest indexRequest = new IndexRequest(indexName);
      indexRequest.source(JSON.toJSONString(esOrderInfoIndex), XContentType.JSON);
      indexRequest.timeout(TimeValue.timeValueSeconds(1));
      indexRequest.setRefreshPolicy(WriteRequest.RefreshPolicy.WAIT_UNTIL);
      indexRequest.create(true);
      indexRequest.id(esOrderInfoIndex.getOrderId().toString());
      requestList.add(indexRequest);

      startOrderId++;
    }

    ExecutorService executorService = Executors.newFixedThreadPool(10);
    CountDownLatch downLatch = new CountDownLatch(requestList.size());
    for (IndexRequest indexRequest : requestList) {
      executorService.execute(() -> {
        try {
          IndexResponse index = restHighLevelClient.index(indexRequest, RequestOptions.DEFAULT);
        } catch (Throwable t) {
        } finally {
          downLatch.countDown();
        }
      });
    }
    downLatch.await();
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

  private EsOrderInfoIndexOBJ buildMyRandomEsObj(Long orderId) {
    EsOrderInfoIndexOBJ obj = new EsOrderInfoIndexOBJ();
    obj.setOrderId(orderId);
    obj.setOrderStatus(OrderUtil.getOrderStatus());
    obj.setUserId(438944209L);
    obj.setCreateTime(new Date());
    obj.setContactName("廖祥");
    obj.setContactPhone("15680747337");
    obj.setGoodsList(OrderUtil.getGoodsInfo());
    return obj;
  }
}