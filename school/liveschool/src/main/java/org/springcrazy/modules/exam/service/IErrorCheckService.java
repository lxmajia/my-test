
package org.springcrazy.modules.exam.service;

import org.springcrazy.modules.exam.entity.ErrorCheck;
import org.springcrazy.modules.exam.vo.ErrorCheckVO;
import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.core.metadata.IPage;

/**
 * 试题纠错表 服务类
 *
 * @author TongZhou
 * @since 2020-12-17
 */
public interface IErrorCheckService extends IService<ErrorCheck> {

	/**
	 * 自定义分页
	 *
	 * @param page
	 * @param errorCheck
	 * @return
	 */
	IPage<ErrorCheckVO> selectErrorCheckPage(IPage<ErrorCheckVO> page, ErrorCheckVO errorCheck);

}
