package cn.xwlin.configcenter.holder;

import cn.xwlin.configcenter.dto.MyConfigCheckDTO;
import cn.xwlin.configcenter.vo.GetConfigData;
import cn.xwlin.configcenter.vo.HttpResp;
import com.google.common.collect.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.web.context.request.async.DeferredResult;

import javax.annotation.PostConstruct;
import java.util.*;

/**
 * @author xiang.liao
 * @create 2024/5/6
 */
@Component
public class ConfigChangeRequestHolder {

  @Autowired
  private ConfigChangeHolder configChangeHolder;
  private static ListMultimap<String, DeferredResult<HttpResp<GetConfigData>>> myWatchRequests = Multimaps.synchronizedListMultimap(ArrayListMultimap.create());

  public void addHolder(String key, Long oldVersion, DeferredResult<HttpResp<GetConfigData>> result) {
    String requestHolderCacheKey = key + "###" + oldVersion;
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
          if (!configChangeHolder.serviceOk()) {
            try {
              Thread.sleep(1000);
            } catch (Throwable t) {
            }
            continue;
          }

          // 无数据，空转
          if (CollectionUtils.isEmpty(myWatchRequests.keys())) {
            try {
              Thread.sleep(500);
            } catch (Throwable t) {
            }
            continue;
          }

          Iterator<Map.Entry<String, Collection<DeferredResult<HttpResp<GetConfigData>>>>> iterator = myWatchRequests.asMap().entrySet().iterator();
          while (iterator.hasNext()) {
            Map.Entry<String, Collection<DeferredResult<HttpResp<GetConfigData>>>> next = iterator.next();
            String key = next.getKey();

            // 过滤出需要处理的（已经设置了返回值和已经超时的，就不处理了）
            List<DeferredResult<HttpResp<GetConfigData>>> validDefered = Lists.newArrayList();
            for (DeferredResult<HttpResp<GetConfigData>> stringDeferredResult : next.getValue()) {
              if (!stringDeferredResult.isSetOrExpired()) {
                validDefered.add(stringDeferredResult);
              }
            }
            if (CollectionUtils.isEmpty(validDefered)) {
              iterator.remove();
              continue;
            }

            String[] split = key.split("###");
            MyConfigCheckDTO checkVO = configChangeHolder.checkConfigChange(split[0], Long.valueOf(split[1]));

            if (!checkVO.isConfigExist() || !checkVO.isChange()) {
              // 配置不存在或者没更新，等待下次执行
              continue;
            }

            GetConfigData getConfigData = new GetConfigData();
            getConfigData.setOldVersion(checkVO.getOldVersion());
            getConfigData.setNewVersion(checkVO.getNewVersion());
            getConfigData.setUniqueKey(checkVO.getUniqueKey());
            getConfigData.setConfigValue(checkVO.getNewConfigValue());
            HttpResp resp = HttpResp.succuess(getConfigData);
            validDefered.forEach(v -> {
              v.setResult(resp);
            });
            iterator.remove();
          }
        }
      }
    }).start();
  }
}
