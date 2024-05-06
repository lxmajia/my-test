package cn.xwlin.logic.action;

import cn.xwlin.common.action.DemoCmd;
import cn.xwlin.common.proto.HelloReq;
import com.iohao.game.action.skeleton.annotation.ActionController;
import com.iohao.game.action.skeleton.annotation.ActionMethod;
import com.iohao.game.action.skeleton.core.exception.MsgException;
import org.assertj.core.util.Lists;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.IntStream;

@Component
@ActionController(DemoCmd.cmd)
public class DemoAction {
  /**
   * 示例 here 方法
   *
   * @param helloReq helloReq
   * @return HelloReq
   */
  @ActionMethod(DemoCmd.here)
  public HelloReq here(HelloReq helloReq) {
    HelloReq newHelloReq = new HelloReq();
    newHelloReq.setName(helloReq.getName() + ", I'm here ");
    return newHelloReq;
  }

  /**
   * 示例 异常机制演示
   *
   * @param helloReq helloReq
   * @return HelloReq
   * @throws MsgException e
   */
  @ActionMethod(DemoCmd.jackson)
  public HelloReq jackson(HelloReq helloReq) {
    String jacksonName = "jackson";
    if (!jacksonName.equals(helloReq.getName())) {
      throw new MsgException(100, "异常机制测试，name 必须是 jackson !");
    }

    helloReq.setName(helloReq.getName() + ", hello, jackson !");

    return helloReq;
  }

  /**
   * 示例 返回 List 数据
   *
   * @return list
   */
  @ActionMethod(DemoCmd.list)
  public List<HelloReq> list() {
    // 得到一个 List 列表数据，并返回给请求端
    List<HelloReq> result = Lists.list();
    for (int i = 0; i < 5; i++) {
      HelloReq helloReq = new HelloReq();
      helloReq.setName("data:" + i);
      result.add(helloReq);
    }
    return result;
  }
}