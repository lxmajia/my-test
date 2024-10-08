package org.springcrazy.common.tool;

import com.aliyuncs.DefaultAcsClient;
import org.springcrazy.core.tool.sms.IMsgSender;
import org.springcrazy.core.tool.sms.sender.AliyunSMS;
import org.springcrazy.core.tool.sms.sender.TencentSMS;
import org.springcrazy.core.tool.vod.AliVodUtil;
import org.springcrazy.modules.cms.entity.WebsiteProfile;

import java.util.Map;

/**
 * <pre>
 * 消息发送器工厂类
 * </pre>
 *
 * @author TongZhou
 * @since 2019/6/15.
 */
public class MsgVodFactory {

    /**
     * 根据消息类型获取对应的消息发送器
     *
     * @return IMsgSender
     */
    public static DefaultAcsClient getMsgSender(String supplier) {

        switch (supplier) {
            case WebsiteProfile.ALIYUN:
                Map<String, String> config = ProfileConfig.getConfig(WebsiteProfile.ALIYUN_VOD);
                String appid = config.get("appid");
                String appkey = config.get("appkey");
                return AliVodUtil.createDefaultAcsClient(appid, appkey);
            case WebsiteProfile.TENCENTYUN:
                break;
            case WebsiteProfile.BAIJIAYUN:

                break;
            default:
                break;
        }
        return null;
    }


}
