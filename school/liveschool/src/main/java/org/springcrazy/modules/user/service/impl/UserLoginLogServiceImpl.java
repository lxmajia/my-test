
package org.springcrazy.modules.user.service.impl;

import org.springcrazy.modules.user.entity.UserLoginLog;
import org.springcrazy.modules.user.vo.UserLoginLogVO;
import org.springcrazy.modules.user.mapper.UserLoginLogMapper;
import org.springcrazy.modules.user.service.IUserLoginLogService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.core.metadata.IPage;

/**
 *  服务实现类
 *
 * @author TongZhou
 * @since 2020-05-06
 */
@Service
public class UserLoginLogServiceImpl extends ServiceImpl<UserLoginLogMapper, UserLoginLog> implements IUserLoginLogService {

	@Override
	public IPage<UserLoginLogVO> selectUserLoginLogPage(IPage<UserLoginLogVO> page, UserLoginLogVO userLoginLog) {
		return page.setRecords(baseMapper.selectUserLoginLogPage(page, userLoginLog));
	}

}
