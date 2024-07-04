
package org.springcrazy.modules.exam.dto;

import org.springcrazy.modules.exam.entity.ExampaperRecord;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 考试记录表数据传输对象实体类
 *
 * @author TongZhou
 * @since 2020-12-02
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class ExampaperRecordDTO extends ExampaperRecord {
	private static final long serialVersionUID = 1L;

}
