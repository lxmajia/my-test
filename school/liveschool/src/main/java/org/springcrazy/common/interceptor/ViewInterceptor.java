
package org.springcrazy.common.interceptor;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springcrazy.common.cache.CacheNames;
import org.springcrazy.core.secure.utils.SecureUtil;
import org.springcrazy.core.tool.api.R;
import org.springcrazy.core.tool.api.ResultCode;
import org.springcrazy.core.tool.constant.CrazyConstant;
import org.springcrazy.core.tool.jackson.JsonUtil;
import org.springcrazy.core.tool.utils.DateUtil;
import org.springcrazy.core.tool.utils.RedisUtil;
import org.springcrazy.core.tool.utils.StringUtil;
import org.springcrazy.core.tool.utils.WebUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

/**
 * 访客记录
 *
 * @author TongZhou
 */
@Slf4j
@AllArgsConstructor
public class ViewInterceptor extends HandlerInterceptorAdapter {

	private RedisUtil redisUtil;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
		log.info("记录访客记录，请求接口：{}，请求IP：{}，请求参数：{}", request.getRequestURI(), WebUtil.getIP(request), JsonUtil.toJson(request.getParameterMap()));
		String dateStr = DateUtil.format(DateUtil.now(), DateUtil.PATTERN_DATE);
		//时间为10天
		redisUtil.sSetAndTime(CacheNames.VIEWLOG+"_"+ dateStr,864000,WebUtil.getIP(request));
		return true;
	}

}
