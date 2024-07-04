
package org.springcrazy.modules.exam.dto;

import org.springcrazy.modules.exam.entity.QuestionRecord;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 考试试题记录表数据传输对象实体类
 *
 * @author TongZhou
 * @since 2021-01-05
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class QuestionRecordDTO extends QuestionRecord {
	private static final long serialVersionUID = 1L;

}
