
package org.springcrazy.modules.msg.dto;

import org.springcrazy.modules.msg.entity.MsgRange;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 消息范围 一对多中间表数据传输对象实体类
 *
 * @author TongZhou
 * @since 2020-05-18
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class MsgRangeDTO extends MsgRange {
	private static final long serialVersionUID = 1L;

}
