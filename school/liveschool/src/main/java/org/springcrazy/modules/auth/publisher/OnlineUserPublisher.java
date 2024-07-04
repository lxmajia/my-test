

package org.springcrazy.modules.auth.publisher;

import org.springcrazy.core.log.event.ApiLogEvent;
import org.springcrazy.core.secure.AuthInfo;
import org.springcrazy.core.tool.utils.SpringUtil;
import org.springcrazy.core.tool.utils.WebUtil;
import org.springcrazy.modules.auth.event.OnlineUserEvent;
import org.springcrazy.modules.system.entity.UserInfo;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * 在线用户记录事件发送
 *
 * @author TongZhou
 */
public class OnlineUserPublisher {

	public static void publishEvent(AuthInfo authInfo, Map<String, Object> cityInfo) {
		Map<String, Object> event = new HashMap<>(16);
		event.put("authInfo", authInfo);
		event.put("cityInfo", cityInfo);
		SpringUtil.publishEvent(new OnlineUserEvent(event));
	}

}
