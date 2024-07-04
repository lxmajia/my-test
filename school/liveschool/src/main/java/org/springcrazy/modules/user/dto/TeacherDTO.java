
package org.springcrazy.modules.user.dto;

import org.springcrazy.modules.user.entity.Teacher;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 讲师数据传输对象实体类
 *
 * @author TongZhou
 * @since 2020-04-28
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class TeacherDTO extends Teacher {
	private static final long serialVersionUID = 1L;

}
