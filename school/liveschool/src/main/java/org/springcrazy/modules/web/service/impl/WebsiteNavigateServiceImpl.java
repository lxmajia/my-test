
package org.springcrazy.modules.web.service.impl;

import org.springcrazy.modules.web.entity.WebsiteNavigate;
import org.springcrazy.modules.web.vo.WebsiteNavigateVO;
import org.springcrazy.modules.web.mapper.WebsiteNavigateMapper;
import org.springcrazy.modules.web.service.IWebsiteNavigateService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.core.metadata.IPage;

/**
 * 导航表 服务实现类
 *
 * @author TongZhou
 * @since 2020-05-09
 */
@Service
public class WebsiteNavigateServiceImpl extends ServiceImpl<WebsiteNavigateMapper, WebsiteNavigate> implements IWebsiteNavigateService {

	@Override
	public IPage<WebsiteNavigateVO> selectWebsiteNavigatePage(IPage<WebsiteNavigateVO> page, WebsiteNavigateVO websiteNavigate) {
		return page.setRecords(baseMapper.selectWebsiteNavigatePage(page, websiteNavigate));
	}

}
