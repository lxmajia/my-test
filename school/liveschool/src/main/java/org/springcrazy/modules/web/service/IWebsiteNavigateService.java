
package org.springcrazy.modules.web.service;

import org.springcrazy.modules.web.entity.WebsiteNavigate;
import org.springcrazy.modules.web.vo.WebsiteNavigateVO;
import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.core.metadata.IPage;

/**
 * 导航表 服务类
 *
 * @author TongZhou
 * @since 2020-05-09
 */
public interface IWebsiteNavigateService extends IService<WebsiteNavigate> {

	/**
	 * 自定义分页
	 *
	 * @param page
	 * @param websiteNavigate
	 * @return
	 */
	IPage<WebsiteNavigateVO> selectWebsiteNavigatePage(IPage<WebsiteNavigateVO> page, WebsiteNavigateVO websiteNavigate);

}
