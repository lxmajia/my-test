
package org.springcrazy.modules.auth.granter;

import io.jsonwebtoken.Claims;
import lombok.AllArgsConstructor;
import org.springcrazy.core.launch.constant.TokenConstant;
import org.springcrazy.core.secure.utils.SecureUtil;
import org.springcrazy.core.tool.utils.DigestUtil;
import org.springcrazy.core.tool.utils.Func;
import org.springcrazy.modules.auth.enums.BladeUserEnum;
import org.springcrazy.modules.system.entity.UserInfo;
import org.springcrazy.modules.system.service.IUserService;
import org.springcrazy.modules.user.service.IStudentService;
import org.springframework.stereotype.Component;

import java.util.Objects;

/**
 * RefreshTokenGranter
 *
 * @author TongZhou
 */
@Component
@AllArgsConstructor
public class RefreshTokenGranter implements ITokenGranter {

	public static final String GRANT_TYPE = "refresh_token";

	private IUserService userService;
	private IStudentService studentService;

	@Override
	public UserInfo grant(TokenParameter tokenParameter) {
		String grantType = tokenParameter.getArgs().getStr("grantType");
		String refreshToken = tokenParameter.getArgs().getStr("refreshToken");
		UserInfo userInfo = null;
		if (Func.isNoneBlank(grantType, refreshToken) && grantType.equals(TokenConstant.REFRESH_TOKEN)) {
			Claims claims = SecureUtil.parseJWT(refreshToken);
			String tokenType = Func.toStr(Objects.requireNonNull(claims).get(TokenConstant.TOKEN_TYPE));
			if (tokenType.equals(TokenConstant.REFRESH_TOKEN)) {
				String userType = tokenParameter.getArgs().getStr("userType");
				if (userType.equals(BladeUserEnum.WEB.getName())) {
					userInfo= userService.userInfo(Func.toLong(claims.get(TokenConstant.USER_ID)));
				} else if (userType.equals(BladeUserEnum.APP.getName())) {
					userInfo= userService.userInfo(Func.toLong(claims.get(TokenConstant.USER_ID)));
				} else if (userType.equals(BladeUserEnum.STUDENT.getName())) {
					userInfo = studentService.userInfo(Func.toInt(claims.get(TokenConstant.USER_ID)));
				} else {
					userInfo= userService.userInfo(Func.toLong(claims.get(TokenConstant.USER_ID)));
				}

			}
		}
		return userInfo;
	}
}
