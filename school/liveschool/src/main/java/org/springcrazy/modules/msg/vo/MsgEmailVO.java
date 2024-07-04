
package org.springcrazy.modules.msg.vo;

import org.springcrazy.modules.msg.entity.MsgEmail;
import lombok.Data;
import lombok.EqualsAndHashCode;
import io.swagger.annotations.ApiModel;

/**
 * 邮件发送记录视图实体类
 *
 * @author TongZhou
 * @since 2020-05-18
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "MsgEmailVO对象", description = "邮件发送记录")
public class MsgEmailVO extends MsgEmail {
	private static final long serialVersionUID = 1L;

}
