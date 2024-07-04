

package org.springcrazy.modules.auth.event;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import io.jsonwebtoken.Claims;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springcrazy.core.launch.constant.TokenConstant;
import org.springcrazy.core.launch.props.CrazyProperties;
import org.springcrazy.core.launch.server.ServerInfo;
import org.springcrazy.core.log.constant.EventConstant;
import org.springcrazy.core.log.event.ApiLogEvent;
import org.springcrazy.core.log.feign.ILogClient;
import org.springcrazy.core.log.model.LogApi;
import org.springcrazy.core.log.utils.LogAbstractUtil;
import org.springcrazy.core.secure.AuthInfo;
import org.springcrazy.core.secure.CrazyUser;
import org.springcrazy.core.secure.registry.SecureRegistry;
import org.springcrazy.core.secure.utils.SecureUtil;
import org.springcrazy.core.tool.utils.DateUtil;
import org.springcrazy.core.tool.utils.Func;
import org.springcrazy.modules.auth.entity.OnlineUser;
import org.springcrazy.modules.auth.service.OnlineUserService;
import org.springcrazy.modules.system.entity.UserInfo;
import org.springcrazy.modules.web.entity.StatUserArea;
import org.springcrazy.modules.web.service.IStatUserAreaService;
import org.springframework.context.event.EventListener;
import org.springframework.core.annotation.Order;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;


/**
 * 异步监听在线用户记录事件
 *
 * @author TongZhou
 */
@Slf4j
@AllArgsConstructor
@Component
public class OnlineUserListener {

	private OnlineUserService onlineUserService;
	private SecureRegistry secureRegistry;
	private IStatUserAreaService iStatUserAreaService;

	@Async
	@Order
	@EventListener(OnlineUserEvent.class)
	public void saveOnlineUser(OnlineUserEvent event) {
		Map<String, Object> source = (Map<String, Object>) event.getSource();
		Map<String, Object> cityInfo = (Map<String, Object>) source.get("cityInfo");
		AuthInfo authInfo = (AuthInfo) source.get("authInfo");

		//单点判断
		if(secureRegistry.isSingleLogin()){
			//踢掉之前已经登录的token
			onlineUserService.checkLoginOnUser(authInfo.getAccount(),authInfo.getAccessToken());
		}
		//根据登录用户获取用户区域信息进行保存
		Claims claims = SecureUtil.parseJWT(authInfo.getAccessToken());
		Integer userId = Func.toInt(claims.get(TokenConstant.USER_ID));
		StatUserArea statUserArea = new StatUserArea();
		statUserArea.setUserId(userId);
		StatUserArea area = iStatUserAreaService.getOne(new QueryWrapper<StatUserArea>(statUserArea));
		if(Func.isNull(area)){
			area = statUserArea;
		}
		area.setUpdateTime(DateUtil.now());
		area.setIp(Func.toStr(cityInfo.get("ip")));
		area.setArea(Func.toInt(cityInfo.get("regionCode")));
		area.setCity(Func.toInt(cityInfo.get("cityCode")));
		area.setProvince(Func.toInt(cityInfo.get("proCode")));
		iStatUserAreaService.saveOrUpdate(area);
	}

}
