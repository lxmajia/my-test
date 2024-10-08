
package org.springcrazy.modules.edu.dto;

import org.springcrazy.modules.edu.entity.CoursePackage;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 数据传输对象实体类
 *
 * @author TongZhou
 * @since 2020-05-05
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class CoursePackageDTO extends CoursePackage {
	private static final long serialVersionUID = 1L;

}
