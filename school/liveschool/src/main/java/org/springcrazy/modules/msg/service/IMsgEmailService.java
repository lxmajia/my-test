
package org.springcrazy.modules.msg.service;

import org.springcrazy.modules.msg.entity.MsgEmail;
import org.springcrazy.modules.msg.entity.MsgSystem;
import org.springcrazy.modules.msg.vo.MsgEmailVO;
import org.springcrazy.core.mp.base.BaseService;
import com.baomidou.mybatisplus.core.metadata.IPage;

/**
 * 邮件发送记录 服务类
 *
 * @author TongZhou
 * @since 2020-05-18
 */
public interface IMsgEmailService extends BaseService<MsgEmail> {

	/**
	 * 自定义分页
	 *
	 * @param page
	 * @param msgEmail
	 * @return
	 */
	IPage<MsgEmailVO> selectMsgEmailPage(IPage<MsgEmailVO> page, MsgEmailVO msgEmail);

	void sendMsg(MsgEmail msgEmail);

	boolean saveMsgEmail(MsgEmail msgEmail);
}
