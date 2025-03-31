package cn.xwlin.configcenter.timer;


import cn.xwlin.configcenter.holder.ClientConfigCacheManager;
import cn.xwlin.configcenter.vo.GetConfigData;
import cn.xwlin.configcenter.vo.HttpResp;
import com.alibaba.fastjson2.JSONObject;
import com.alibaba.fastjson2.TypeReference;
import org.springframework.util.StringUtils;

import java.util.UUID;
import java.util.logging.Logger;

public class ConfigFetchTimerTask implements Runnable {
  private static Logger logger = Logger.getLogger("WlinConfig");

  @Override
  public void run() {
    long start = System.currentTimeMillis();
    String uuid = UUID.randomUUID().toString();
    logger.info("REFRESH:" + uuid + ":S");
    try {
      String refreshConfig = ConfigFetchNetwork.refreshConfig();
      if (!StringUtils.hasLength(refreshConfig)) {
        return;
      }
      TypeReference<HttpResp<GetConfigData>> typeReference = new TypeReference<HttpResp<GetConfigData>>() {
      };
      HttpResp<GetConfigData> getConfigDataHttpResp = JSONObject.parseObject(refreshConfig, typeReference);
      if (getConfigDataHttpResp != null && getConfigDataHttpResp.getBody() != null) {
        ClientConfigCacheManager.refreshTime = getConfigDataHttpResp.getBody().getNextTimeMills();
        ClientConfigCacheManager.refreshCacheMap(getConfigDataHttpResp.getBody());
      }
    } catch (Throwable t) {
      // 报警还是怎么处理都行
    } finally {
      long end = System.currentTimeMillis();
      logger.info("REFRESH:" + uuid + ":E:" + (end - start) + "ms");
    }
  }
}
