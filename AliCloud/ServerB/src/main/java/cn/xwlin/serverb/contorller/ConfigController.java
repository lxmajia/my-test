package cn.xwlin.serverb.contorller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author xiang.liao
 * @create 2023/11/10
 */
@RestController
public class ConfigController {

  @Value("${name:Default}")
  private String name;

  @RequestMapping("getName")
  public String getConfig() {
    return name;
  }
}
