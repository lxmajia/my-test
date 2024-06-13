package cn.xwlin.configcenter.timer;


import cn.xwlin.configcenter.holder.ConfigCenterConfigHold;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;
import java.util.Objects;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

/**
 * Created by minji on 15/11/16.
 */
public class ConfigFetchNetwork {
  private static OkHttpClient okHttpClient = null;
  private static Object lock = new Object();

  public static String checkAppModule() {
    // 拉取配置，并更新缓存
    Request request = new Request.Builder().url(ConfigCenterConfigHold.getCheckAppModuleUrl()).build();
    try (Response response = new OkHttpClient().newCall(request).execute()) {
      if (!response.isSuccessful()) {
        throw new IOException("Unexpected code " + response);
      }
      return Objects.requireNonNull(response.body()).string();
    } catch (IOException e) {
      return null;
    }
  }

  public static String getAllConfig() {
    // 拉取配置，并更新缓存
    Request request = new Request.Builder().url(ConfigCenterConfigHold.getAllConfig()).build();
    try (Response response = new OkHttpClient().newCall(request).execute()) {
      if (!response.isSuccessful()) {
        throw new IOException("Unexpected code " + response);
      }
      return Objects.requireNonNull(response.body()).string();
    } catch (IOException e) {
      return null;
    }
  }

  public static String getSysConfig() {
    // 拉取配置，并更新缓存
    Request request = new Request.Builder().url(ConfigCenterConfigHold.getSysConfig()).build();
    try (Response response = new OkHttpClient().newCall(request).execute()) {
      if (!response.isSuccessful()) {
        throw new IOException("Unexpected code " + response);
      }
      return Objects.requireNonNull(response.body()).string();
    } catch (IOException e) {
      return null;
    }
  }

  public static String refreshConfig() {
    if (okHttpClient == null) {
      synchronized (lock) {
        okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(3, TimeUnit.SECONDS)
                .readTimeout(ConfigCenterConfigHold.timeout, TimeUnit.MILLISECONDS)
                .build();
      }
    }

    // 拉取配置，并更新缓存
    Request request = new Request.Builder().url(ConfigCenterConfigHold.getRefreshConfig()).build();
    try (Response response = okHttpClient.newCall(request).execute()) {
      if (!response.isSuccessful()) {
        throw new IOException("Unexpected code " + response);
      }
      return Objects.requireNonNull(response.body()).string();
    } catch (IOException e) {
      return null;
    }
  }

  public static String getConfigValue(String configKey) {
    // 拉取配置，并更新缓存
    Request request = new Request.Builder().url(ConfigCenterConfigHold.getConfigValue(configKey)).build();
    try (Response response = new OkHttpClient().newCall(request).execute()) {
      if (!response.isSuccessful()) {
        throw new IOException("Unexpected code " + response);
      }
      return Objects.requireNonNull(response.body()).string();
    } catch (IOException e) {
      return null;
    }
  }
}
