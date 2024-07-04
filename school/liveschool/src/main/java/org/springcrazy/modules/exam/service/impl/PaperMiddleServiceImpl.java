
package org.springcrazy.modules.exam.service.impl;

import org.springcrazy.modules.exam.entity.PaperMiddle;
import org.springcrazy.modules.exam.vo.PaperMiddleVO;
import org.springcrazy.modules.exam.mapper.PaperMiddleMapper;
import org.springcrazy.modules.exam.service.IPaperMiddleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.core.metadata.IPage;

/**
 * 大题（试卷试题类型中间表） 服务实现类
 *
 * @author TongZhou
 * @since 2020-12-15
 */
@Service
public class PaperMiddleServiceImpl extends ServiceImpl<PaperMiddleMapper, PaperMiddle> implements IPaperMiddleService {

	@Override
	public IPage<PaperMiddleVO> selectPaperMiddlePage(IPage<PaperMiddleVO> page, PaperMiddleVO paperMiddle) {
		return page.setRecords(baseMapper.selectPaperMiddlePage(page, paperMiddle));
	}

}
