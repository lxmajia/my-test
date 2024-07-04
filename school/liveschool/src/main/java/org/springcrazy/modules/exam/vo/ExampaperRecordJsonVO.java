
package org.springcrazy.modules.exam.vo;

import org.springcrazy.modules.exam.entity.ExampaperRecordJson;
import lombok.Data;
import lombok.EqualsAndHashCode;
import io.swagger.annotations.ApiModel;

/**
 * 视图实体类
 *
 * @author TongZhou
 * @since 2021-01-05
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "ExampaperRecordJsonVO对象", description = "ExampaperRecordJsonVO对象")
public class ExampaperRecordJsonVO extends ExampaperRecordJson {
	private static final long serialVersionUID = 1L;

}
