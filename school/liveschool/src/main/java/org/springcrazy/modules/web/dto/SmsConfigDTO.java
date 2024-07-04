
package org.springcrazy.modules.web.dto;

import org.springcrazy.modules.web.entity.SmsConfig;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 数据传输对象实体类
 *
 * @author TongZhou
 * @since 2020-05-09
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class SmsConfigDTO extends SmsConfig {
	private static final long serialVersionUID = 1L;

}
