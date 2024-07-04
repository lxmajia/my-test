
package org.springcrazy.modules.coursecard.dto;

import org.springcrazy.modules.coursecard.entity.Card;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 课程卡主表数据传输对象实体类
 *
 * @author TongZhou
 * @since 2021-03-31
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class CardDTO extends Card {
	private static final long serialVersionUID = 1L;

}
