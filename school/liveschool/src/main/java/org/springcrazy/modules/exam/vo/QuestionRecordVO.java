
package org.springcrazy.modules.exam.vo;

import org.springcrazy.modules.exam.entity.QuestionRecord;
import lombok.Data;
import lombok.EqualsAndHashCode;
import io.swagger.annotations.ApiModel;

/**
 * 考试试题记录表视图实体类
 *
 * @author TongZhou
 * @since 2021-01-05
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "QuestionRecordVO对象", description = "考试试题记录表")
public class QuestionRecordVO extends QuestionRecord {
	private static final long serialVersionUID = 1L;

}
