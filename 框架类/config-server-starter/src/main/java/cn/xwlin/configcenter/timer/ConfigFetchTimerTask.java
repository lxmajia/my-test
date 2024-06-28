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
        logger.info("REFRESH:" + uuid + ":E");
        //每次加载完休息2秒钟，避免机器时间差
        Thread.sleep(2000);
      } catch (Throwable t) {
        // 报警还是怎么处理都行
      }
    }
  }
}
