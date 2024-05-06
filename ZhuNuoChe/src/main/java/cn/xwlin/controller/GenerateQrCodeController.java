package cn.xwlin.controller;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import cn.xwlin.cons.QrCodeStatus;
import cn.xwlin.controller.req.BindSceneCodeRequest;
import cn.xwlin.controller.resp.BindSceneCodeResp;
import cn.xwlin.entity.UserInfo;
import cn.xwlin.entity.WxQrCode;
import cn.xwlin.mapper.WxQrCodeMapper;
import cn.xwlin.service.UserService;

import java.util.Date;

@RestController
@RequestMapping("/generate")
public class GenerateQrCodeController {

  @Autowired
  private WxQrCodeMapper wxQrCodeMapper;


  @RequestMapping("/qrCode")
  public RestResponse qrCode(Integer count) {
    if (count == null) {
      count = 10;
    }
    Date date = new Date();
    WxQrCode wxQrCode = new WxQrCode();
    for (int i = 0; i < count; i++) {
      String code = RandomStringUtils.randomAlphanumeric(32).toUpperCase();
      wxQrCode.setId(null);
      wxQrCode.setSceneCode(code);
      wxQrCode.setQrCodeStatus(QrCodeStatus.NO_GENERATE.getStatus());
      wxQrCode.setCreated(date);
      wxQrCodeMapper.insertSelective(wxQrCode);
    }
    return RestResponse.succuess();
  }
}
