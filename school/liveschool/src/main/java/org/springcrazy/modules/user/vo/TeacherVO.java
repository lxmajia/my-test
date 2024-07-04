
package org.springcrazy.modules.user.vo;

import org.springcrazy.modules.user.entity.Teacher;
import lombok.Data;
import lombok.EqualsAndHashCode;
import io.swagger.annotations.ApiModel;

/**
 * 讲师视图实体类
 *
 * @author TongZhou
 * @since 2020-04-28
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "TeacherVO对象", description = "讲师")
public class TeacherVO extends Teacher {
	private static final long serialVersionUID = 1L;

}
