
package org.springcrazy.modules.edu.dto;

import org.springcrazy.modules.edu.entity.TrxorderDetail;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 流水表数据传输对象实体类
 *
 * @author TongZhou
 * @since 2020-05-09
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class TrxorderDetailDTO extends TrxorderDetail {
	private static final long serialVersionUID = 1L;

}
