
package org.springcrazy.modules.web.service;

import org.apache.ibatis.annotations.Param;
import org.springcrazy.modules.web.entity.SensitiveWords;
import org.springcrazy.modules.web.vo.SensitiveWordsVO;
import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.core.metadata.IPage;

/**
 * 网站敏感词 服务类
 *
 * @author TongZhou
 * @since 2020-05-09
 */
public interface ISensitiveWordsService extends IService<SensitiveWords> {

	/**
	 * 自定义分页
	 *
	 * @param page
	 * @param sensitiveWords
	 * @return
	 */
	IPage<SensitiveWordsVO> selectSensitiveWordsPage(IPage<SensitiveWordsVO> page, SensitiveWordsVO sensitiveWords);

	/**
	 * 自定义分页
	 *
	 * @param page
	 * @param sensitiveWords
	 * @return
	 */
	IPage<SensitiveWords> selectSensitiveWordsPageList (IPage<SensitiveWords> page, SensitiveWords sensitiveWords);

}
