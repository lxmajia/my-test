
package org.springcrazy.modules.web.service.impl;

import org.springcrazy.modules.web.entity.StatUserArea;
import org.springcrazy.modules.web.vo.StatUserAreaVO;
import org.springcrazy.modules.web.mapper.StatUserAreaMapper;
import org.springcrazy.modules.web.service.IStatUserAreaService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.core.metadata.IPage;

/**
 * 用户ip登录所在区域记录 服务实现类
 *
 * @author TongZhou
 * @since 2020-05-20
 */
@Service
public class StatUserAreaServiceImpl extends ServiceImpl<StatUserAreaMapper, StatUserArea> implements IStatUserAreaService {

	@Override
	public IPage<StatUserAreaVO> selectStatUserAreaPage(IPage<StatUserAreaVO> page, StatUserAreaVO statUserArea) {
		return page.setRecords(baseMapper.selectStatUserAreaPage(page, statUserArea));
	}

}
