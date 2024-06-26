package cn.xwlin.configcenter.controller;

import cn.dev33.satoken.annotation.SaIgnore;
import cn.dev33.satoken.stp.StpUtil;
import cn.xwlin.configcenter.service.ManagerService;
import cn.xwlin.configcenter.vo.request.LoginInfoReq;
import cn.xwlin.configcenter.vo.request.LoginReq;
import cn.xwlin.configcenter.vo.resp.AppModuleResp;
import cn.xwlin.configcenter.vo.resp.HttpResp;
import cn.xwlin.configcenter.vo.resp.LoginInfoResp;
import cn.xwlin.configcenter.vo.resp.LoginResp;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


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
  public HttpResp<LoginResp> login(@RequestBody LoginReq loginReq) {
    return managerService.login(loginReq.getUsername(), loginReq.getPassword());
  }

  @RequestMapping("/info")
  public HttpResp<LoginInfoResp> info() {
    return managerService.loginInfo();
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
