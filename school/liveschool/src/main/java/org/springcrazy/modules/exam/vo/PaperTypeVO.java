
package org.springcrazy.modules.exam.vo;

import org.springcrazy.modules.exam.entity.PaperType;
import lombok.Data;
import lombok.EqualsAndHashCode;
import io.swagger.annotations.ApiModel;

/**
 * 试卷类型表视图实体类
 *
 * @author TongZhou
 * @since 2020-12-01
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "PaperTypeVO对象", description = "试卷类型表")
public class PaperTypeVO extends PaperType {
	private static final long serialVersionUID = 1L;

}
