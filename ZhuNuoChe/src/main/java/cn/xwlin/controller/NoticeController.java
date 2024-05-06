package cn.xwlin.controller;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import cn.xwlin.controller.req.BindSceneCodeRequest;
import cn.xwlin.controller.req.NoticeRequest;
import cn.xwlin.controller.resp.BindSceneCodeResp;
import cn.xwlin.controller.resp.NoticeResp;
import cn.xwlin.entity.UserInfo;
import cn.xwlin.service.UserService;

@Slf4j
@RestController
@RequestMapping("/notice")
public class NoticeController {

  @Autowired
  private UserService userService;

  @RequestMapping("/toNotice")

  public RestResponse toNotice(@RequestBody NoticeRequest req) {
    NoticeResp noticeResp = new NoticeResp();
    noticeResp.setNoticeSuccess(true);
    noticeResp.setFailReason("SUCCESS");
    log.info("#通知#req:{}", JSON.toJSONString(req));
    return RestResponse.succuess(noticeResp);
  }
}
