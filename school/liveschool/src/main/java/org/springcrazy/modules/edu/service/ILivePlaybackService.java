
package org.springcrazy.modules.edu.service;

import org.springcrazy.modules.edu.entity.LivePlayback;
import org.springcrazy.modules.edu.vo.LivePlaybackVO;
import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.core.metadata.IPage;

/**
 * 直播回放管理 服务类
 *
 * @author TongZhou
 * @since 2020-11-10
 */
public interface ILivePlaybackService extends IService<LivePlayback> {

	/**
	 * 自定义分页
	 *
	 * @param page
	 * @param livePlayback
	 * @return
	 */
	IPage<LivePlaybackVO> selectLivePlaybackPage(IPage<LivePlaybackVO> page, LivePlaybackVO livePlayback);

}
