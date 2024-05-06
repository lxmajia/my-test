package cn.xwlin.server;

import cn.binarywang.wx.miniapp.api.WxMaService;
import cn.binarywang.wx.miniapp.bean.WxMaCodeLineColor;
import cn.binarywang.wx.miniapp.bean.WxMaJscode2SessionResult;
import cn.binarywang.wx.miniapp.bean.WxMaTemplateData;
import cn.binarywang.wx.miniapp.bean.WxMaUniformMessage;
import com.google.common.collect.Lists;
import me.chanjar.weixin.common.error.WxErrorException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import cn.xwlin.cons.WxAppInfo;
import cn.xwlin.util.TimeDateUtil;

import java.io.File;
import java.util.List;

@Service
public class WxServer {
  @Autowired
  private WxMaService wxMaService;


  public WxMaJscode2SessionResult getOpenId(String jsCode) {
    try {
      return wxMaService.jsCode2SessionInfo(jsCode);
    } catch (WxErrorException e) {
      throw new RuntimeException(e);
    }
  }

  public File getQrcode(String sceneCode, String pageUrl) {
    try {
      return wxMaService.getQrcodeService().createWxaCodeUnlimit(sceneCode, pageUrl, true,
              "release", 180, true, (WxMaCodeLineColor) null, true);
    } catch (WxErrorException e) {
      throw new RuntimeException(e);
    }
  }

  public void sendNuoCheWxMsg(String toOpenId, String appPage, String formId, String carNum, String nuoCheMsg) throws WxErrorException {
    WxMaTemplateData car = new WxMaTemplateData();
    car.setName("car_number1");
    car.setValue(carNum);

    WxMaTemplateData time = new WxMaTemplateData();
    time.setName("time3");
    time.setValue(TimeDateUtil.date());

    WxMaTemplateData msg = new WxMaTemplateData();
    msg.setName("thing4");
    msg.setValue(nuoCheMsg);

    List<WxMaTemplateData> data = Lists.newArrayList(car, time, msg);

    WxMaUniformMessage wxMaUniformMessage = new WxMaUniformMessage();
    wxMaUniformMessage.setAppid(WxAppInfo.WX_APP_ID);
    wxMaUniformMessage.setToUser(toOpenId);
    wxMaUniformMessage.setTemplateId(WxAppInfo.NUO_CHE_MSG_TEMPALTE_ID);
    wxMaUniformMessage.setPage(appPage);
    wxMaUniformMessage.setFormId(formId);
    wxMaUniformMessage.setData(data);
    wxMaService.getMsgService().sendUniformMsg(wxMaUniformMessage);
  }

  public void sendNuoCheBackWxMsg(String toOpenId, String appPage, String formId, String nuoCheMsg) throws WxErrorException {
    WxMaTemplateData msg = new WxMaTemplateData();
    msg.setName("thing1");
    msg.setValue(nuoCheMsg);

    WxMaTemplateData time = new WxMaTemplateData();
    time.setName("time2");
    time.setValue(TimeDateUtil.date());

    List<WxMaTemplateData> data = Lists.newArrayList(time, msg);

    WxMaUniformMessage wxMaUniformMessage = new WxMaUniformMessage();
    wxMaUniformMessage.setAppid(WxAppInfo.WX_APP_ID);
    wxMaUniformMessage.setTemplateId(WxAppInfo.NUO_CHE_BACK_MSG_TEMPLATE_ID);
    wxMaUniformMessage.setPage(appPage);
    wxMaUniformMessage.setFormId(formId);
    wxMaUniformMessage.setData(data);
    wxMaService.getMsgService().sendUniformMsg(wxMaUniformMessage);
  }
}
