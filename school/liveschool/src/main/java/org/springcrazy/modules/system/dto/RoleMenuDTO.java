
package org.springcrazy.modules.system.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springcrazy.modules.system.entity.RoleMenu;

/**
 * 数据传输对象实体类
 *
 * @author TongZhou
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class RoleMenuDTO extends RoleMenu {
	private static final long serialVersionUID = 1L;

}
