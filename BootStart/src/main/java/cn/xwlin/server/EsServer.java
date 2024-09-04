package cn.xwlin.server;

import cn.xwlin.vo.EsQueryResult;
import com.alibaba.fastjson2.JSONObject;
import com.alibaba.fastjson2.TypeReference;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author xiang.liao
 * @create 2024/9/4
 */
@Component
public class EsServer {

  @Autowired
  private RestHighLevelClient restHighLevelClient;

  public <T> EsQueryResult<T> queryByTemplate(SearchSourceBuilder queryBuilder, Class<T> resultType, String indexName) throws IOException {
    SearchRequest searchRequest = new SearchRequest();
    searchRequest.indices(indexName);//指定要查询的索引
    //3.将 SearchSourceBuilder 添加到 SearchRequest中
    searchRequest.source(queryBuilder);
    //4.执行查询
    SearchResponse searchResponse = restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);

    EsQueryResult<T> tEsQueryResult = new EsQueryResult<>();
    tEsQueryResult.setTook(searchResponse.getTook().getMillis());
    tEsQueryResult.setCount(searchResponse.getHits().getTotalHits().value);

    if (tEsQueryResult.getCount() == 0) {
      return tEsQueryResult;
    }
    List<T> list = new ArrayList<>();
    for (SearchHit hit : searchResponse.getHits().getHits()) {
      T t = JSONObject.parseObject(hit.getSourceAsString(), resultType);
      list.add(t);
    }
    tEsQueryResult.setList(list);
    return tEsQueryResult;
  }
}
