
package org.springcrazy.modules.exam.service.impl;

import org.springcrazy.modules.exam.entity.PaperType;
import org.springcrazy.modules.exam.vo.PaperTypeVO;
import org.springcrazy.modules.exam.mapper.PaperTypeMapper;
import org.springcrazy.modules.exam.service.IPaperTypeService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.core.metadata.IPage;

/**
 * 试卷类型表 服务实现类
 *
 * @author TongZhou
 * @since 2020-12-01
 */
@Service
public class PaperTypeServiceImpl extends ServiceImpl<PaperTypeMapper, PaperType> implements IPaperTypeService {

	@Override
	public IPage<PaperTypeVO> selectPaperTypePage(IPage<PaperTypeVO> page, PaperTypeVO paperType) {
		return page.setRecords(baseMapper.selectPaperTypePage(page, paperType));
	}

}
