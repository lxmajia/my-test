package cn.xwlin.controller;

import cn.xwlin.object.EsOrderInfoIndexOBJ;
import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
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
import org.elasticsearch.rest.RestStatus;
import org.elasticsearch.xcontent.XContentType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    public String createData(Integer count) throws Exception {
        int successCount = 0;
        for (int i = 0; i < count; i++) {
            EsOrderInfoIndexOBJ esOrderInfoIndex = buildRandomEsObj(1L);
            IndexRequest indexRequest = new IndexRequest();
            indexRequest.source(JSON.toJSONString(esOrderInfoIndex), XContentType.JSON);
            indexRequest.id(esOrderInfoIndex.getId());
            indexRequest.timeout(TimeValue.timeValueSeconds(1));
            indexRequest.setRefreshPolicy(WriteRequest.RefreshPolicy.WAIT_UNTIL);
            indexRequest.create(true);
            IndexResponse index = restHighLevelClient.index(indexRequest, RequestOptions.DEFAULT);
            if (index.status().equals(RestStatus.OK)) {
                successCount++;
            }
        }
        return "SUCCESS：" + successCount;
    }

    private EsOrderInfoIndexOBJ buildRandomEsObj(Long orderId) {
        EsOrderInfoIndexOBJ obj = new EsOrderInfoIndexOBJ();


        return obj;
    }
}