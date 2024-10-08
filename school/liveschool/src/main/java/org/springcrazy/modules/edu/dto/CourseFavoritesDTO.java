
package org.springcrazy.modules.edu.dto;

import org.springcrazy.modules.edu.entity.CourseFavorites;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 收藏数据传输对象实体类
 *
 * @author TongZhou
 * @since 2020-04-25
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class CourseFavoritesDTO extends CourseFavorites {
	private static final long serialVersionUID = 1L;

}
