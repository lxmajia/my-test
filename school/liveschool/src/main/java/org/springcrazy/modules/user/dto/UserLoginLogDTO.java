
package org.springcrazy.modules.user.dto;

import org.springcrazy.modules.user.entity.UserLoginLog;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 数据传输对象实体类
 *
 * @author TongZhou
 * @since 2020-05-06
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class UserLoginLogDTO extends UserLoginLog {
	private static final long serialVersionUID = 1L;

}
