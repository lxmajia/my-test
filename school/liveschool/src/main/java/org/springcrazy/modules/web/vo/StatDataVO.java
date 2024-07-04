
package org.springcrazy.modules.web.vo;

import org.springcrazy.modules.web.entity.StatData;
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
@ApiModel(value = "StatDataVO对象", description = "StatDataVO对象")
public class StatDataVO extends StatData {
	private static final long serialVersionUID = 1L;

}
