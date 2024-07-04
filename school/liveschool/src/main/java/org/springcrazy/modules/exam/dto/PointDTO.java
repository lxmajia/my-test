
package org.springcrazy.modules.exam.dto;

import org.springcrazy.modules.exam.entity.Point;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 考点数据传输对象实体类
 *
 * @author TongZhou
 * @since 2020-12-01
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class PointDTO extends Point {
	private static final long serialVersionUID = 1L;

}
