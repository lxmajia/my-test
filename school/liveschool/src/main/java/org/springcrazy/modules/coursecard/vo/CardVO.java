
package org.springcrazy.modules.coursecard.vo;

import org.springcrazy.modules.coursecard.entity.Card;
import lombok.Data;
import lombok.EqualsAndHashCode;
import io.swagger.annotations.ApiModel;

/**
 * 课程卡主表视图实体类
 *
 * @author TongZhou
 * @since 2021-03-31
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "CardVO对象", description = "课程卡主表")
public class CardVO extends Card {
	private static final long serialVersionUID = 1L;

}
