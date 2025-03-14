package cn.xwlin.configcenter.timer;


import cn.xwlin.configcenter.holder.ClientConfigCacheManager;
import cn.xwlin.configcenter.vo.GetConfigData;
import cn.xwlin.configcenter.vo.HttpResp;
import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;
import com.alibaba.fastjson2.TypeReference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import java.util.TimerTask;
import java.util.UUID;

public class ConfigFetchTimerTask implements Runnable {
  private static final Object lock = new Object();

  private static Logger logger = LoggerFactory.getLogger(ConfigFetchTimerTask.class);

  @Override
  public void run() {
    while (true) {
      long start = System.currentTimeMillis();
      String uuid = UUID.randomUUID().toString();
      logger.info("REFRESH:" + uuid + ":S");
      try {
        String refreshConfig = ConfigFetchNetwork.refreshConfig();
        if (!StringUtils.hasLength(refreshConfig)) {
          continue;
        }
        TypeReference<HttpResp<GetConfigData>> typeReference = new TypeReference<HttpResp<GetConfigData>>() {
        };
        HttpResp<GetConfigData> getConfigDataHttpResp = JSONObject.parseObject(refreshConfig, typeReference);
        if (getConfigDataHttpResp != null && getConfigDataHttpResp.getBody() != null) {
          ClientConfigCacheManager.refreshTime = getConfigDataHttpResp.getBody().getNextTimeMills();
          ClientConfigCacheManager.refreshCacheMap(getConfigDataHttpResp.getBody());
        }
        //(刚获取到有更新的数据，那么休息两秒钟缓冲一下。)
        Thread.sleep(2000);
      } catch (Throwable t) {
        // 报警还是怎么处理都行
      } finally {
        long end = System.currentTimeMillis();
        logger.info("REFRESH:" + uuid + ":E:" + (end - start) + "ms");
      }
    }
  }
}
