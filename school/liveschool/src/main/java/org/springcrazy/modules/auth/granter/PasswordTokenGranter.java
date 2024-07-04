
package org.springcrazy.modules.auth.granter;

import lombok.AllArgsConstructor;
import org.springcrazy.common.cache.CacheNames;
import org.springcrazy.core.log.exception.ServiceException;
import org.springcrazy.core.tool.utils.*;
import org.springcrazy.modules.auth.enums.BladeUserEnum;
import org.springcrazy.modules.auth.utils.TokenUtil;
import org.springcrazy.modules.system.entity.UserInfo;
import org.springcrazy.modules.system.service.IUserService;
import org.springcrazy.modules.user.service.IStudentService;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

/**
 * PasswordTokenGranter
 *
 * @author TongZhou
 */
@Component
@AllArgsConstructor
public class PasswordTokenGranter implements ITokenGranter {

	public static final String GRANT_TYPE = "password";

	private IUserService userService;
	private IStudentService studentService;

	@Override
	public UserInfo grant(TokenParameter tokenParameter) {

		String tenantId = tokenParameter.getArgs().getStr("tenantId");
		String account = tokenParameter.getArgs().getStr("account");
		String password = tokenParameter.getArgs().getStr("password");
		UserInfo userInfo = null;
		if (Func.isNoneBlank(account, password)) {
			// 获取用户类型
			String userType = tokenParameter.getArgs().getStr("userType");
			// 根据不同用户类型调用对应的接口返回数据，用户可自行拓展
			if (userType.equals(BladeUserEnum.WEB.getName())) {
				userInfo = userService.userInfo(tenantId, account, DigestUtil.encrypt(password));
			} else if (userType.equals(BladeUserEnum.APP.getName())) {
				userInfo = studentService.userInfo(account, DigestUtil.encrypt(password));
			} else if (userType.equals(BladeUserEnum.STUDENT.getName())) {
				userInfo = studentService.userInfo(account, DigestUtil.encrypt(password));
			} else {
				userInfo = userService.userInfo(tenantId, account, DigestUtil.encrypt(password));
			}
		}
		return userInfo;
	}

}
