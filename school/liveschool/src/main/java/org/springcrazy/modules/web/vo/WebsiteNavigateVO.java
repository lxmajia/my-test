
package org.springcrazy.modules.web.vo;

import org.springcrazy.modules.web.entity.WebsiteNavigate;
import lombok.Data;
import lombok.EqualsAndHashCode;
import io.swagger.annotations.ApiModel;

/**
 * 导航表视图实体类
 *
 * @author TongZhou
 * @since 2020-05-09
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "WebsiteNavigateVO对象", description = "导航表")
public class WebsiteNavigateVO extends WebsiteNavigate {
	private static final long serialVersionUID = 1L;

}
