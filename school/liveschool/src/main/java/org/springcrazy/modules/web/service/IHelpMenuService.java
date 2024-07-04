
package org.springcrazy.modules.web.service;

import org.springcrazy.modules.edu.vo.SubjectVO;
import org.springcrazy.modules.web.entity.HelpMenu;
import org.springcrazy.modules.web.vo.HelpMenuVO;
import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.core.metadata.IPage;

import java.util.List;

/**
 * 帮助菜单 服务类
 *
 * @author TongZhou
 * @since 2020-05-09
 */
public interface IHelpMenuService extends IService<HelpMenu> {

	/**
	 * 自定义分页
	 *
	 * @param page
	 * @param helpMenu
	 * @return
	 */
	IPage<HelpMenuVO> selectHelpMenuPage(IPage<HelpMenuVO> page, HelpMenuVO helpMenu);

	/**
	 * 树形结构
	 *
	 * @return
	 */
	List<HelpMenuVO> tree();

}
