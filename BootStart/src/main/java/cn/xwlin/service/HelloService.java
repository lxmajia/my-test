package cn.xwlin.service;


import cn.xwlin.constans.DsName;
import cn.xwlin.dao.UserMapper;
import cn.xwlin.dao.datasource.DS;
import cn.xwlin.vo.UserFullInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author MaJiaXueYuan.lx
 * @ClassName HelloService.java
 * @createTime 2022-5-12-0012
 */
@Slf4j
@Service
@DS(DsName.SLAVE)
public class HelloService {

    @Autowired
    private UserMapper userMapper;

    public String hello(String name) {
        return "hello" + name;
    }

    public UserFullInfo getId(Long id) {
        return userMapper.findById(id);
    }
}
