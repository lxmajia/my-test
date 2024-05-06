package cn.xwlin.controller;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import cn.xwlin.controller.req.BindSceneCodeRequest;
import cn.xwlin.controller.req.ScanQueryRequest;
import cn.xwlin.controller.resp.BindSceneCodeResp;
import cn.xwlin.controller.resp.ScanQueryResp;
import cn.xwlin.entity.UserInfo;
import cn.xwlin.service.UserService;

@RestController
@RequestMapping("/bind")
public class BindSceneCodeController {

  @Autowired
  private UserService userService;

  @RequestMapping("/bindScene")
  public RestResponse bindScene(@RequestBody BindSceneCodeRequest req) {
    BindSceneCodeResp bindSceneCodeResp = new BindSceneCodeResp();
    // 通过JScode得到OpenId
    String openId = req.getJsCode();
    UserInfo byOpenId = userService.findByOpenId(openId);
    if (byOpenId != null && StringUtils.isNotBlank(byOpenId.getSceneCode())) {
      bindSceneCodeResp.setBindSuccess(false);
      bindSceneCodeResp.setFailReason("已经绑定过了，请先解绑或联系客服");
      return RestResponse.succuess(bindSceneCodeResp);
    }
    if (byOpenId == null) {
      byOpenId = new UserInfo();
    }
    byOpenId.setSceneCode(req.getSceneCode());
    byOpenId.setOpenId(openId);
    byOpenId.setQrCodeId(0);
    byOpenId.setMobilePhone(req.getMobileNo());
    byOpenId.setNewEnergyFlag(req.getNewEnergyFlag());
    byOpenId.setCarNum(req.getCarNum());
    byOpenId.setScanNotice(req.getScanNotice());
    byOpenId.setWxNoticeFlag(req.getWxNoticeFlag());
    byOpenId.setSmsNoticeFlag(req.getSmsNoticeFlag());
    byOpenId.setTelNoticeFlag(req.getTelNoticeFlag());
    int i = userService.updateOrNewUser(byOpenId);
    if (i <= 0) {
      bindSceneCodeResp.setBindSuccess(false);
      bindSceneCodeResp.setFailReason("绑定失败，请联系客服");
      return RestResponse.succuess(bindSceneCodeResp);
    }
    bindSceneCodeResp.setBindSuccess(true);
    bindSceneCodeResp.setFailReason("SUCCESS");
    return RestResponse.succuess(bindSceneCodeResp);
  }
}
