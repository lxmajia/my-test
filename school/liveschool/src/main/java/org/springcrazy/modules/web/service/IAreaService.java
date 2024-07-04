
package org.springcrazy.modules.web.service;

import org.springcrazy.modules.web.entity.Area;
import org.springcrazy.modules.web.vo.AreaVO;
import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.core.metadata.IPage;

/**
 *  服务类
 *
 * @author TongZhou
 * @since 2020-05-19
 */
public interface IAreaService extends IService<Area> {

	/**
	 * 自定义分页
	 *
	 * @param page
	 * @param area
	 * @return
	 */
	IPage<AreaVO> selectAreaPage(IPage<AreaVO> page, AreaVO area);

}
