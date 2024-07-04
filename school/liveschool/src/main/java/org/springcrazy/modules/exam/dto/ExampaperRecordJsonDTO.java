
package org.springcrazy.modules.exam.dto;

import org.springcrazy.modules.exam.entity.ExampaperRecordJson;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 数据传输对象实体类
 *
 * @author TongZhou
 * @since 2021-01-05
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class ExampaperRecordJsonDTO extends ExampaperRecordJson {
	private static final long serialVersionUID = 1L;

}
