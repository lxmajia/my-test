
package org.springcrazy.modules.msg.vo;

import org.springcrazy.modules.msg.entity.MsgReceive;
import lombok.Data;
import lombok.EqualsAndHashCode;
import io.swagger.annotations.ApiModel;

/**
 * 站内信视图实体类
 *
 * @author TongZhou
 * @since 2020-05-18
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "MsgReceiveVO对象", description = "站内信")
public class MsgReceiveVO extends MsgReceive {
	private static final long serialVersionUID = 1L;

}
