package cn.xwlin.configcenter.holder;

import cn.xwlin.configcenter.dto.MyConfigCheckDTO;
import cn.xwlin.configcenter.vo.GetConfigData;
import cn.xwlin.configcenter.vo.HttpResp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.web.context.request.async.DeferredResult;

import javax.annotation.PostConstruct;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author xiang.liao
 * @create 2024/5/6
 */
@Component
public class ConfigChangeRequestHolder {

  @Autowired
  private ConfigCacheManager configCacheManager;
  private static Map<String, DeferredResult<HttpResp<GetConfigData>>> myWatchRequests = new ConcurrentHashMap<>();

  public void addHolder(String appCode, String moduleCode, String ip, Long lastFetchTime, DeferredResult<HttpResp<GetConfigData>> result) {
    // 应用名+模块名+时间+IP（用来确认当前请求唯一）
    String requestHolderCacheKey = appCode + "$" + moduleCode + "$" + lastFetchTime + "$" + ip;
    myWatchRequests.put(requestHolderCacheKey, result);
  }

  @PostConstruct
  public void deferredResdultCheck() {
    new Thread(new Runnable() {
      @Override
      public void run() {
        System.out.println("###ConfigChangeRequestHolder###START");
        while (true) {
          // 服务还没OK，就稍等执行
          if (!configCacheManager.serviceOk()) {
            try {
              Thread.sleep(1000);
            } catch (Throwable t) {
            }
            continue;
          }

          // 没有请求，空转
          if (CollectionUtils.isEmpty(myWatchRequests)) {
            try {
              Thread.sleep(1000);
            } catch (Throwable t) {
            }
            continue;
          }

          Iterator<Map.Entry<String, DeferredResult<HttpResp<GetConfigData>>>> iterator = myWatchRequests.entrySet().iterator();
          while (iterator.hasNext()) {
            Map.Entry<String, DeferredResult<HttpResp<GetConfigData>>> next = iterator.next();
            String key = next.getKey();
            DeferredResult<HttpResp<GetConfigData>> value = next.getValue();
            if (value.isSetOrExpired()) {
              iterator.remove();
              continue;
            }

            String[] split = key.split("\\$");
            MyConfigCheckDTO checkVO = configCacheManager.checkConfigChange(split[0], split[1], Long.valueOf(split[2]));
            if (checkVO.getNewConfigChangeCount() == 0) {
              // 配置不存在或者没更新，等待下次执行
              continue;
            }
            GetConfigData getConfigData = new GetConfigData();
            getConfigData.setNextTimeMills(checkVO.getNextFetchTime());
            getConfigData.setChangeCount(checkVO.getNewConfigChangeCount());
            getConfigData.setChangeConfigMap(checkVO.getNewConfigValueMap());
            value.setResult(HttpResp.success(getConfigData));
          }
        }
      }
    }).start();
  }
}
