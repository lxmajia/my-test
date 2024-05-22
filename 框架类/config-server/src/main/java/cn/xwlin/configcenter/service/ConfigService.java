package cn.xwlin.configcenter.service;

import cn.xwlin.configcenter.entity.AppInfo;
import cn.xwlin.configcenter.mapper.AppInfoMapper;
import cn.xwlin.configcenter.vo.HttpResp;
import com.mysql.cj.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author xiang.liao
 * @create 2024/5/22
 */
@Service
public class ConfigService {
  @Autowired
  private AppInfoMapper appInfoMapper;

  public HttpResp checkAppModule(String appCode, String moduleCode) {
    if (StringUtils.isNullOrEmpty(appCode) || StringUtils.isNullOrEmpty(moduleCode)) {
      return HttpResp.fail(-1, "ConfigCenter:appCode or moduleCode can not be empty!");
    }
    AppInfo appInfo = appInfoMapper.selectByAppModule(appCode, moduleCode);
    if (appInfo == null) {
      return HttpResp.fail(-1, "ConfigCenter:appCode and moduleCode is not exist!");
    }
    return HttpResp.succuess();
  }
}
