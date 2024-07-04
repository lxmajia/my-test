
package org.springcrazy.modules.exam.dto;

import org.springcrazy.modules.exam.entity.PaperType;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 试卷类型表数据传输对象实体类
 *
 * @author TongZhou
 * @since 2020-12-01
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class PaperTypeDTO extends PaperType {
	private static final long serialVersionUID = 1L;

}
