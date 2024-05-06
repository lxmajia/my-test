package cn.xwlin.service;

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
public class HelloService {


  public String hello(String name) {
    return "hello" + name;
  }

}
