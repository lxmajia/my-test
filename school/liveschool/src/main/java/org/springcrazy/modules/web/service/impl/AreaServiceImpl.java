
package org.springcrazy.modules.web.service.impl;

import org.springcrazy.modules.web.entity.Area;
import org.springcrazy.modules.web.vo.AreaVO;
import org.springcrazy.modules.web.mapper.AreaMapper;
import org.springcrazy.modules.web.service.IAreaService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.core.metadata.IPage;

/**
 *  服务实现类
 *
 * @author TongZhou
 * @since 2020-05-19
 */
@Service
public class AreaServiceImpl extends ServiceImpl<AreaMapper, Area> implements IAreaService {

	@Override
	public IPage<AreaVO> selectAreaPage(IPage<AreaVO> page, AreaVO area) {
		return page.setRecords(baseMapper.selectAreaPage(page, area));
	}

}
