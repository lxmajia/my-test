package cn.xwlin.service;


import cn.xwlin.constans.DsName;
import cn.xwlin.dao.UserMapper;
import cn.xwlin.datasource.anno.DS;
import cn.xwlin.entity.UserInfo;
import cn.xwlin.vo.UserFullInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author MaJiaXueYuan.lx
 * @ClassName HelloService.java
 * @createTime 2022-5-12-0012
 */
@Slf4j
@Service
@DS(DsName.MASTER)
public class HelloService {

  @Autowired
  private UserMapper userMapper;

  public String hello(String name) {
    return "hello" + name;
  }

  public UserFullInfo getId(Long id) {
    return userMapper.findById(id);
  }

  @Transactional
  @DS(DsName.MASTER)
  public int insert(String name) {
    UserInfo userInfo = userMapper.selectById(1L);
    userInfo.setId(null);
    userInfo.setWxName(name);
    int insert = userMapper.insert(userInfo);
    int i = 1 / 0;
    return insert;
  }
}
