
package org.springcrazy.modules.web.vo;

import org.springcrazy.modules.web.entity.StatUserArea;
import lombok.Data;
import lombok.EqualsAndHashCode;
import io.swagger.annotations.ApiModel;

/**
 * 用户ip登录所在区域记录视图实体类
 *
 * @author TongZhou
 * @since 2020-05-20
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "StatUserAreaVO对象", description = "用户ip登录所在区域记录")
public class StatUserAreaVO extends StatUserArea {
	private static final long serialVersionUID = 1L;

}
