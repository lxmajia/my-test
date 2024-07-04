
package org.springcrazy.modules.msg.dto;

import org.springcrazy.modules.msg.entity.MsgMobile;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 手机短信发送记录数据传输对象实体类
 *
 * @author TongZhou
 * @since 2020-05-18
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class MsgMobileDTO extends MsgMobile {
	private static final long serialVersionUID = 1L;

}
