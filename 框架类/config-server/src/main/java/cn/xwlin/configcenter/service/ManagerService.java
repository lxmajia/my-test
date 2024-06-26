package cn.xwlin.configcenter.service;

import cn.dev33.satoken.stp.SaTokenInfo;
import cn.dev33.satoken.stp.StpUtil;
import cn.xwlin.configcenter.entity.AppInfo;
import cn.xwlin.configcenter.entity.SysUser;
import cn.xwlin.configcenter.mapper.AppInfoMapper;
import cn.xwlin.configcenter.mapper.SysUserMapper;
import cn.xwlin.configcenter.vo.resp.AppModuleResp;
import cn.xwlin.configcenter.vo.resp.HttpResp;
import cn.xwlin.configcenter.vo.resp.LoginInfoResp;
import cn.xwlin.configcenter.vo.resp.LoginResp;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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

  public PageInfo<AppModuleResp> listAppModule(int pageNum, int pageSize) {
    PageHelper.startPage(pageNum, pageSize);
    List<AppInfo> appInfos = appInfoMapper.listAll();

    List<AppModuleResp> appModuleResps = Lists.newArrayList();
    for (AppInfo appInfo : appInfos) {
      AppModuleResp appModuleResp = new AppModuleResp();
      appModuleResp.setId(appInfo.getId());
      appModuleResp.setAppCode(appInfo.getAppCode());
      appModuleResp.setAppModule(appInfo.getModuleCode());
      appModuleResps.add(appModuleResp);
    }
    return new PageInfo<>(appModuleResps);
  }

  public HttpResp<LoginInfoResp> loginInfo() {
    Long loginUserId = Long.valueOf(StpUtil.getLoginId().toString());
    SysUser sysUser = sysUserMapper.selectById(loginUserId);
    LoginInfoResp loginInfoResp = new LoginInfoResp();
    loginInfoResp.setNickName(sysUser.getNickName());
    return HttpResp.success(loginInfoResp);
  }
}
