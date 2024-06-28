package cn.xwlin.configcenter.service;

import cn.dev33.satoken.stp.SaTokenInfo;
import cn.dev33.satoken.stp.StpUtil;
import cn.xwlin.configcenter.entity.AppInfo;
import cn.xwlin.configcenter.entity.ConfigInfo;
import cn.xwlin.configcenter.entity.SysConfig;
import cn.xwlin.configcenter.entity.SysUser;
import cn.xwlin.configcenter.holder.ConfigCacheManager;
import cn.xwlin.configcenter.mapper.AppInfoMapper;
import cn.xwlin.configcenter.mapper.ConfigInfoMapper;
import cn.xwlin.configcenter.mapper.SysConfigMapper;
import cn.xwlin.configcenter.mapper.SysUserMapper;
import cn.xwlin.configcenter.vo.request.AppModuleListRequest;
import cn.xwlin.configcenter.vo.request.GetSysConfigReq;
import cn.xwlin.configcenter.vo.request.UpdateConfigInfoReq;
import cn.xwlin.configcenter.vo.request.UpdateSysConfigReq;
import cn.xwlin.configcenter.vo.resp.*;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author xiang.liao
 * @create 2024/6/20
 */
@Service
public class ManagerService {

  @Autowired
  private SysUserMapper sysUserMapper;

  @Autowired
  private AppInfoMapper appInfoMapper;

  @Autowired
  private SysConfigMapper sysConfigMapper;

  @Autowired
  private ConfigInfoMapper configInfoMapper;

  @Autowired
  private ConfigCacheManager configCacheManager;

  public HttpResp<LoginResp> login(String username, String password) {
    SysUser sysUser = sysUserMapper.selectByLogin(username, password);
    if (sysUser == null) {
      return HttpResp.fail(401, "登录失败");
    }
    // 第1步，先登录上
    StpUtil.login(sysUser.getId());
    SaTokenInfo tokenInfo = StpUtil.getTokenInfo();

    LoginResp loginResp = new LoginResp();
    loginResp.setNickName(sysUser.getNickName());
    loginResp.setToken(tokenInfo.getTokenValue());
    return HttpResp.success(loginResp);
  }

  public PageInfo<AppModuleResp> listAppModule(AppModuleListRequest request) {
    PageHelper.startPage(request.getPageNum(), request.getPageSize());
    List<AppModuleResp> appInfos = appInfoMapper.listAll(request.getAppCode(), request.getModuleCode());
    return new PageInfo<>(appInfos);
  }

  public HttpResp<Map<String, List<AppModuleStructResp>>> listAppModuleStruct() {
    Map<String, List<AppModuleStructResp>> appModuleMapping = Maps.newHashMap();
    List<AppModuleResp> appInfos = appInfoMapper.listAll(null, null);
    for (AppModuleResp appInfo : appInfos) {
      List<AppModuleStructResp> appModuleStructModuleData = appModuleMapping.get(appInfo.getAppCode());
      if (appModuleStructModuleData == null) {
        appModuleStructModuleData = Lists.newArrayList();
      }
      AppModuleStructResp moduleStructModuleData = new AppModuleStructResp();
      moduleStructModuleData.setModuleId(appInfo.getId());
      moduleStructModuleData.setModuleCode(appInfo.getAppModule());
      appModuleStructModuleData.add(moduleStructModuleData);

      appModuleMapping.put(appInfo.getAppCode(), appModuleStructModuleData);
    }
    return HttpResp.success(appModuleMapping);
  }

  public PageInfo<SysConfig> getSysConfigList(GetSysConfigReq req) {
    PageHelper.startPage(req.getPageNum(), req.getPageSize());
    List<SysConfig> sysConfigs = sysConfigMapper.listByAppModuleId(req.getAppModuleId(), req.getConfigKey());
    return new PageInfo<>(sysConfigs);
  }

  public HttpResp updateSysConfig(UpdateSysConfigReq req) {
    SysConfig sysConfig = null;
    if (req.getId() != null) {
      sysConfig = sysConfigMapper.selectByPrimaryKey(req.getId());
      if (sysConfig == null) {
        return HttpResp.fail(403, "数据不存在");
      }
      sysConfig.setConfigKey(req.getConfigKey());
      sysConfig.setConfigValue(req.getConfigValue());
      sysConfigMapper.updateByPrimaryKey(sysConfig);
    } else {
      sysConfig = new SysConfig();
      sysConfig.setAppModuleId(req.getAppModuleId());
      sysConfig.setConfigKey(req.getConfigKey());
      sysConfig.setConfigValue(req.getConfigValue());
      sysConfig.setCreateTime(new Date());
      sysConfigMapper.insertSelective(sysConfig);
    }
    return HttpResp.success();
  }

  public HttpResp updateConfigInfo(UpdateConfigInfoReq req) {
    ConfigInfo configInfo = null;

    if (req.getId() != null) {
      configInfo = configInfoMapper.selectByPrimaryKey(req.getId());
      if (configInfo == null) {
        return HttpResp.fail(403, "数据不存在");
      }
      configInfo.setConfigKey(req.getConfigKey());
      configInfo.setConfigValue(req.getConfigValue());
      configInfo.setModified(new Date());
      configInfo.setVersion(configInfo.getVersion() + 1);
      configInfoMapper.updateByPrimaryKeySelective(configInfo);
    } else {
      AppInfo appInfo = appInfoMapper.selectByPrimaryKey(req.getAppModuleId());
      if (appInfo == null) {
        return HttpResp.fail(-1, "应用不存在!");
      }
      configInfo = new ConfigInfo();
      configInfo.setAppModuleId(req.getAppModuleId());
      configInfo.setConfigKey(req.getConfigKey());
      configInfo.setConfigValue(req.getConfigValue());
      configInfo.setModified(new Date());
      configInfo.setConfigType("1");
      configInfo.setAppCode(appInfo.getAppCode());
      configInfo.setModuleCode(appInfo.getModuleCode());
      configInfo.setUniqueKey(appInfo.getAppCode() + "$" + appInfo.getModuleCode() + "$" + configInfo.getConfigKey());
      configInfo.setVersion(1L);
      configInfoMapper.insertSelective(configInfo);
    }
    // 异步刷新当前配置
    configCacheManager.refreshByApi(configInfo.getId());
    return HttpResp.success();
  }


  public PageInfo<ConfigInfo> getConfigInfoList(GetSysConfigReq req) {
    PageHelper.startPage(req.getPageNum(), req.getPageSize());
    List<ConfigInfo> configInfos = configInfoMapper.listAppModuleConfig(req.getAppModuleId(), req.getConfigKey());
    return new PageInfo<>(configInfos);
  }


  public HttpResp<LoginInfoResp> loginInfo() {
    Long loginUserId = Long.valueOf(StpUtil.getLoginId().toString());
    SysUser sysUser = sysUserMapper.selectById(loginUserId);
    LoginInfoResp loginInfoResp = new LoginInfoResp();
    loginInfoResp.setNickName(sysUser.getNickName());
    return HttpResp.success(loginInfoResp);
  }
}
