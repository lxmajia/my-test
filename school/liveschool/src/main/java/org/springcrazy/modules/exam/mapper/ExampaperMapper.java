
package org.springcrazy.modules.exam.mapper;

import org.springcrazy.modules.exam.entity.Exampaper;
import org.springcrazy.modules.exam.vo.ExampaperVO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import java.util.List;

/**
 * 考试试卷表 Mapper 接口
 *
 * @author TongZhou
 * @since 2020-12-01
 */
public interface ExampaperMapper extends BaseMapper<Exampaper> {

	/**
	 * 自定义分页
	 *
	 * @param page
	 * @param exampaper
	 * @return
	 */
	List<ExampaperVO> selectExampaperPage(IPage page, ExampaperVO exampaper);

}
