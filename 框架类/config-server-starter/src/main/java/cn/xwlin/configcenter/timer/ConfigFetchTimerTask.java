package cn.xwlin.configcenter.timer;


import cn.xwlin.configcenter.holder.ConfigCacheManeger;
import cn.xwlin.configcenter.vo.GetConfigData;
import cn.xwlin.configcenter.vo.HttpResp;
import com.alibaba.fastjson2.JSONObject;
import com.alibaba.fastjson2.TypeReference;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;
import java.util.Objects;
import java.util.TimerTask;

/**
 * Created by minji on 15/11/16.
 */
public class ConfigFetchTimerTask extends TimerTask {
  private static ConfigFetchTimerTask hotSwitchTimerTask;
  private static final Object lock = new Object();

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
    String refreshConfig = ConfigFetchNetwork.refreshConfig();
    TypeReference<HttpResp<GetConfigData>> typeReference = new TypeReference<HttpResp<GetConfigData>>() {};
    HttpResp<GetConfigData> getConfigDataHttpResp = JSONObject.parseObject(refreshConfig, typeReference);
    if (getConfigDataHttpResp != null && getConfigDataHttpResp.getBody() != null) {
      ConfigCacheManeger.(getConfigDataHttpResp.getBody());
    }
  }
}
