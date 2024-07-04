
package org.springcrazy.modules.coursecard.vo;

import io.swagger.annotations.ApiModelProperty;
import org.springcrazy.modules.coursecard.entity.CardCourse;
import lombok.Data;
import lombok.EqualsAndHashCode;
import io.swagger.annotations.ApiModel;

/**
 * 课程卡课程表视图实体类
 *
 * @author TongZhou
 * @since 2021-04-02
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "CardCourseVO对象", description = "课程卡课程表")
public class CardCourseVO extends CardCourse {
	private static final long serialVersionUID = 1L;
	/**
	 * CardCourse的课程名称
	 */
	@ApiModelProperty(value = "CardCourse的课程名称")
	String courseName;
}
