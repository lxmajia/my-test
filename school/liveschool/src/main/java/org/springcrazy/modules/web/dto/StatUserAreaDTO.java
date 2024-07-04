
package org.springcrazy.modules.web.dto;

import org.springcrazy.modules.web.entity.StatUserArea;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 用户ip登录所在区域记录数据传输对象实体类
 *
 * @author TongZhou
 * @since 2020-05-20
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class StatUserAreaDTO extends StatUserArea {
	private static final long serialVersionUID = 1L;

}
