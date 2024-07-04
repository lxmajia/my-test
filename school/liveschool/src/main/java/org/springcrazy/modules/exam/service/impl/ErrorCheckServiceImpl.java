
package org.springcrazy.modules.exam.service.impl;

import org.springcrazy.modules.exam.entity.ErrorCheck;
import org.springcrazy.modules.exam.vo.ErrorCheckVO;
import org.springcrazy.modules.exam.mapper.ErrorCheckMapper;
import org.springcrazy.modules.exam.service.IErrorCheckService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.core.metadata.IPage;

/**
 * 试题纠错表 服务实现类
 *
 * @author TongZhou
 * @since 2020-12-17
 */
@Service
public class ErrorCheckServiceImpl extends ServiceImpl<ErrorCheckMapper, ErrorCheck> implements IErrorCheckService {

	@Override
	public IPage<ErrorCheckVO> selectErrorCheckPage(IPage<ErrorCheckVO> page, ErrorCheckVO errorCheck) {
		return page.setRecords(baseMapper.selectErrorCheckPage(page, errorCheck));
	}

}
