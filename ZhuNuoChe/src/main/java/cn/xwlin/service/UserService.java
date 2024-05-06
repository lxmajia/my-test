package cn.xwlin.service;

import org.springframework.stereotype.Service;
import cn.xwlin.entity.UserInfo;
import cn.xwlin.mapper.UserInfoMapper;

@Service
public class UserService {

  private UserInfoMapper userInfoMapper;

  public UserInfo findBySceneCode(String sceneCode) {
    return userInfoMapper.findBySceneCode(sceneCode);
  }

  public UserInfo findByOpenId(String openId) {
    return userInfoMapper.findByOpenId(openId);
  }

  public int updateOrNewUser(UserInfo userInfo) {
    if (userInfo.getId() == null) {
      return userInfoMapper.insertSelective(userInfo);
    }
    return userInfoMapper.updateByPrimaryKeySelective(userInfo);
  }

}
