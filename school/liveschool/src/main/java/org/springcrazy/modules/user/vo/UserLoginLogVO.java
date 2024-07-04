
package org.springcrazy.modules.user.vo;

import org.springcrazy.modules.user.entity.UserLoginLog;
import lombok.Data;
import lombok.EqualsAndHashCode;
import io.swagger.annotations.ApiModel;

/**
 * 视图实体类
 *
 * @author TongZhou
 * @since 2020-05-06
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "UserLoginLogVO对象", description = "UserLoginLogVO对象")
public class UserLoginLogVO extends UserLoginLog {
	private static final long serialVersionUID = 1L;

}
