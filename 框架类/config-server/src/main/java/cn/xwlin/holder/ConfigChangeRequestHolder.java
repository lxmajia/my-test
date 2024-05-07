package cn.xwlin.holder;

import cn.xwlin.vo.MyConfigCheckVO;
import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Lists;
import com.google.common.collect.Multimap;
import com.google.common.collect.Multimaps;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.web.context.request.async.DeferredResult;

import javax.annotation.PostConstruct;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @author xiang.liao
 * @create 2024/5/6
 */
@Component
public class ConfigChangeRequestHolder {

  @Autowired
  private ConfigChangeHolder configChangeHolder;
  private static Multimap<String, DeferredResult<String>> myWatchRequests =
          Multimaps.synchronizedListMultimap(ArrayListMultimap.create());



  public void addCheck(String key, DeferredResult<String> result) {
    myWatchRequests.put(key, result);
  }

  @PostConstruct
  public void deferredResdultCheck() {
    new Thread(new Runnable() {
      @Override
      public void run() {
        System.out.println("###ConfigChangeRequestHolder###START");
        while (true) {
          if (CollectionUtils.isEmpty(myWatchRequests.keys())) {
            continue;
          }

          if (!configChangeHolder.serviceOk()) {
            try {
              Thread.sleep(1000);
            } catch (Throwable t) {

            }
            continue;
          }

          Iterator<Map.Entry<String, Collection<DeferredResult<String>>>> iterator =
                  myWatchRequests.asMap().entrySet().iterator();
          while (iterator.hasNext()) {
            Map.Entry<String, Collection<DeferredResult<String>>> next = iterator.next();
            String key = next.getKey();
            // 校验是不是都返回了
            List<DeferredResult<String>> validDefered = Lists.newArrayList();
            for (DeferredResult<String> stringDeferredResult : next.getValue()) {
              if (!stringDeferredResult.isSetOrExpired()) {
                validDefered.add(stringDeferredResult);
              }
            }
            if (CollectionUtils.isEmpty(validDefered)) {
              iterator.remove();
              continue;
            }

            String[] split = key.split("###");
            MyConfigCheckVO checkVO = configChangeHolder.checkConfigChange(split[0], Long.valueOf(split[1]));

            if (!checkVO.isConfigExist()) {
              validDefered.forEach(v -> {
                v.setResult("没有找到配置");
              });
              iterator.remove();
              continue;
            }

            if (checkVO.isChange()) {
              validDefered.forEach(v -> {
                v.setResult("有更新，有更新，版本" + checkVO.getOldVersion() + " => " + checkVO.getNewVersion());
              });
              iterator.remove();
              continue;
            }

            if (!CollectionUtils.isEmpty(validDefered)) {
              next.setValue(validDefered);
            }
          }
        }
      }
    }).start();
  }
}
