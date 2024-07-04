
package org.springcrazy.modules.web.vo;

import org.springcrazy.modules.web.entity.Area;
import lombok.Data;
import lombok.EqualsAndHashCode;
import io.swagger.annotations.ApiModel;

/**
 * 视图实体类
 *
 * @author TongZhou
 * @since 2020-05-19
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "AreaVO对象", description = "AreaVO对象")
public class AreaVO extends Area {
	private static final long serialVersionUID = 1L;

}
