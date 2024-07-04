
package org.springcrazy.modules.msg.service;

import org.springcrazy.modules.msg.entity.MsgEmail;
import org.springcrazy.modules.msg.entity.MsgMobile;
import org.springcrazy.modules.msg.vo.MsgMobileVO;
import org.springcrazy.core.mp.base.BaseService;
import com.baomidou.mybatisplus.core.metadata.IPage;

/**
 * 手机短信发送记录 服务类
 *
 * @author TongZhou
 * @since 2020-05-18
 */
public interface IMsgMobileService extends BaseService<MsgMobile> {

	/**
	 * 自定义分页
	 *
	 * @param page
	 * @param msgMobile
	 * @return
	 */
	IPage<MsgMobileVO> selectMsgMobilePage(IPage<MsgMobileVO> page, MsgMobileVO msgMobile);

	void sendMsg(MsgMobile msgMobile,String params);

	boolean saveMsgMobile(MsgMobile msgMobile);

}
