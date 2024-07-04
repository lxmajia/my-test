
package org.springcrazy.modules.edu.service;

import org.springcrazy.modules.edu.entity.CourseFavorites;
import org.springcrazy.modules.edu.vo.CourseFavoritesVO;
import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.core.metadata.IPage;

/**
 * 收藏 服务类
 *
 * @author TongZhou
 * @since 2020-04-25
 */
public interface ICourseFavoritesService extends IService<CourseFavorites> {

	/**
	 * 自定义分页
	 *
	 * @param page
	 * @param courseFavorites
	 * @return
	 */
	IPage<CourseFavoritesVO> selectCourseFavoritesPage(IPage<CourseFavoritesVO> page, CourseFavoritesVO courseFavorites);

}
