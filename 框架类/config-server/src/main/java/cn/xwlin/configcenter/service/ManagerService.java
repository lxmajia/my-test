package cn.xwlin.configcenter.service;

import cn.dev33.satoken.stp.SaTokenInfo;
import cn.dev33.satoken.stp.StpUtil;
import cn.xwlin.configcenter.entity.SysConfig;
import cn.xwlin.configcenter.entity.SysUser;
import cn.xwlin.configcenter.mapper.AppInfoMapper;
import cn.xwlin.configcenter.mapper.SysConfigMapper;
import cn.xwlin.configcenter.mapper.SysUserMapper;
import cn.xwlin.configcenter.vo.request.AppModuleListRequest;
import cn.xwlin.configcenter.vo.request.GetSysConfigReq;
import cn.xwlin.configcenter.vo.resp.*;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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


  public HttpResp<LoginInfoResp> loginInfo() {
    Long loginUserId = Long.valueOf(StpUtil.getLoginId().toString());
    SysUser sysUser = sysUserMapper.selectById(loginUserId);
    LoginInfoResp loginInfoResp = new LoginInfoResp();
    loginInfoResp.setNickName(sysUser.getNickName());
    return HttpResp.success(loginInfoResp);
  }
}
