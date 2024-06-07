package cn.xwlin.configcenter.timer;


import cn.xwlin.configcenter.holder.ClientConfigCacheManager;
import cn.xwlin.configcenter.vo.GetConfigData;
import cn.xwlin.configcenter.vo.HttpResp;
import com.alibaba.fastjson2.JSONObject;
import com.alibaba.fastjson2.TypeReference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.TimerTask;
import java.util.UUID;

/**
 * Created by minji on 15/11/16.
 */
public class ConfigFetchTimerTask extends TimerTask {
  private static ConfigFetchTimerTask hotSwitchTimerTask;
  private static final Object lock = new Object();

  private static Logger logger = LoggerFactory.getLogger(ConfigFetchTimerTask.class);

  public static ConfigFetchTimerTask getConfigFetchTimerTaskInstance() {
    if (null == hotSwitchTimerTask) {
      synchronized (lock) {
        if (null == hotSwitchTimerTask) {
          hotSwitchTimerTask = new ConfigFetchTimerTask();
        }
      }
    }
    return hotSwitchTimerTask;
  }

  @Override
  public void run() {
    String uuid = UUID.randomUUID().toString();
    logger.info("REFRESH:" + uuid + ":S");
    String refreshConfig = ConfigFetchNetwork.refreshConfig();
    logger.info("REFRESH:" + uuid + ":E");
    TypeReference<HttpResp<GetConfigData>> typeReference = new TypeReference<HttpResp<GetConfigData>>() {
    };
    HttpResp<GetConfigData> getConfigDataHttpResp = JSONObject.parseObject(refreshConfig, typeReference);
    if (getConfigDataHttpResp != null && getConfigDataHttpResp.getBody() != null) {
      ClientConfigCacheManager.refreshTime = getConfigDataHttpResp.getBody().getNextTimeMills();
      ClientConfigCacheManager.refreshCacheMap(getConfigDataHttpResp.getBody());
    }
  }
}
