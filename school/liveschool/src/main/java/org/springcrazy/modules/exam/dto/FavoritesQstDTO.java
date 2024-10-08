
package org.springcrazy.modules.exam.dto;

import org.springcrazy.modules.exam.entity.FavoritesQst;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 考试试题收藏表数据传输对象实体类
 *
 * @author TongZhou
 * @since 2020-12-17
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class FavoritesQstDTO extends FavoritesQst {
	private static final long serialVersionUID = 1L;

}
