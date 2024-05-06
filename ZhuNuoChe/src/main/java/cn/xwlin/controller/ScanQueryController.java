package cn.xwlin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import cn.xwlin.controller.req.ScanQueryRequest;
import cn.xwlin.controller.resp.ScanQueryResp;
import cn.xwlin.entity.UserInfo;
import cn.xwlin.service.UserService;

@RestController
@RequestMapping("/scan")
public class ScanQueryController {

  @Autowired
  private UserService userService;

  @RequestMapping("/scanQuery")
  public RestResponse scanQuery(@RequestBody ScanQueryRequest req) {
    /**
     * 1：通过sceneCode查询当前绑定的码
     * 2：如果已经绑定了车，那么需要返回给用户提示信息
     * 3：如果未绑定车，那么返回给当前用户可以绑定
     */
    ScanQueryResp scanQueryResp = new ScanQueryResp();
    UserInfo bySceneCode = userService.findBySceneCode(req.getSceneCode());
    if (bySceneCode == null) {
      scanQueryResp.setIsBind(false);
      return RestResponse.succuess(scanQueryResp);
    }
    scanQueryResp.setIsBind(true);
    scanQueryResp.setSceneCode(bySceneCode.getSceneCode());
    scanQueryResp.setScanNotice(bySceneCode.getScanNotice());
    scanQueryResp.setCarNum(bySceneCode.getCarNum());
    scanQueryResp.setBindUserId(bySceneCode.getId());
    scanQueryResp.setNewEnergyFlag(bySceneCode.getNewEnergyFlag());
    scanQueryResp.setSmsNoticeFlag(bySceneCode.getSmsNoticeFlag());
    scanQueryResp.setTelNoticeFlag(bySceneCode.getTelNoticeFlag());
    scanQueryResp.setWxNoticeFlag(bySceneCode.getWxNoticeFlag());
    return RestResponse.succuess(scanQueryResp);
  }
}
