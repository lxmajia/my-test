
package org.springcrazy.modules.coursecard.dto;

import org.springcrazy.modules.coursecard.entity.CardCourse;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 课程卡课程表数据传输对象实体类
 *
 * @author TongZhou
 * @since 2021-04-02
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class CardCourseDTO extends CardCourse {
	private static final long serialVersionUID = 1L;

}
