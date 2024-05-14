package cn.xwlin.logic.action;

import cn.xwlin.common.action.DemoCmd;
import cn.xwlin.common.proto.HelloReq;
import com.iohao.game.action.skeleton.annotation.ActionController;
import com.iohao.game.action.skeleton.annotation.ActionMethod;

@ActionController(DemoCmd.cmd)
public class HelloAction {
  /**
   * 示例 here 方法
   *
   * @param helloReq helloReq
   * @return HelloReq
   */
  @ActionMethod(DemoCmd.hello)
  public HelloReq hello(HelloReq helloReq) {
    HelloReq newHelloReq = new HelloReq();
    System.out.println("hello : " + helloReq.name);
    newHelloReq.name = helloReq.name + "，hello json";
    return newHelloReq;
  }
}