package cn.xwlin.configcenter.controller;

import cn.dev33.satoken.annotation.SaIgnore;
import cn.dev33.satoken.stp.StpUtil;
import cn.xwlin.configcenter.holder.ConfigChangeRequestHolder;
import cn.xwlin.configcenter.service.ConfigService;
import cn.xwlin.configcenter.service.ManagerService;
import cn.xwlin.configcenter.service.SysConfigService;
import cn.xwlin.configcenter.vo.AppModuleResp;
import cn.xwlin.configcenter.vo.GetConfigData;
import cn.xwlin.configcenter.vo.HttpResp;
import cn.xwlin.configcenter.vo.LoginResp;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;


/**
 * @author xiang.liao
 * @create 2024/5/6
 */
@RestController
@RequestMapping("/manager")
public class ManagerController {

  @Autowired
  private ManagerService managerService;

  @RequestMapping("/login")
  @SaIgnore
  public HttpResp<LoginResp> login(String username, String password) {
    return managerService.login(username, password);
  }

  @RequestMapping("/checkLogin")
  public HttpResp<LoginResp> checkLogin() {
    Object loginId = StpUtil.getLoginId();
    return HttpResp.fail(0, "loginId:" + loginId);
  }

  @RequestMapping("/appModuleList")
  public HttpResp<PageInfo<AppModuleResp>> appModuleList(int pageNum, int pageSize) {
    Object loginId = StpUtil.getLoginId();
    return HttpResp.success(managerService.listAppModule(pageNum, pageSize));
  }

}
