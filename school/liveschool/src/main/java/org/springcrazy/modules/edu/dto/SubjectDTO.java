
package org.springcrazy.modules.edu.dto;

import org.springcrazy.modules.edu.entity.Subject;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 专业分类数据传输对象实体类
 *
 * @author TongZhou
 * @since 2020-04-25
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class SubjectDTO extends Subject {
	private static final long serialVersionUID = 1L;

}
