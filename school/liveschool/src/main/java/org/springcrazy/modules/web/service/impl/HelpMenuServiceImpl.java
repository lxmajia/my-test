
package org.springcrazy.modules.web.service.impl;

import org.springcrazy.core.tool.node.ForestNodeMerger;
import org.springcrazy.modules.edu.vo.SubjectVO;
import org.springcrazy.modules.web.entity.HelpMenu;
import org.springcrazy.modules.web.vo.HelpMenuVO;
import org.springcrazy.modules.web.mapper.HelpMenuMapper;
import org.springcrazy.modules.web.service.IHelpMenuService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.core.metadata.IPage;

import java.util.List;

/**
 * 帮助菜单 服务实现类
 *
 * @author TongZhou
 * @since 2020-05-09
 */
@Service
public class HelpMenuServiceImpl extends ServiceImpl<HelpMenuMapper, HelpMenu> implements IHelpMenuService {

	@Override
	public IPage<HelpMenuVO> selectHelpMenuPage(IPage<HelpMenuVO> page, HelpMenuVO helpMenu) {
		return page.setRecords(baseMapper.selectHelpMenuPage(page, helpMenu));
	}

	@Override
	public List<HelpMenuVO> tree() {
		return ForestNodeMerger.merge(baseMapper.tree());
	}
}
