package cn.xwlin.logic.action;

import cn.xwlin.common.action.DemoCmd;
import cn.xwlin.common.proto.HelloReq;
import com.iohao.game.action.skeleton.annotation.ActionController;
import com.iohao.game.action.skeleton.annotation.ActionMethod;
import com.iohao.game.action.skeleton.core.flow.FlowContext;

@ActionController(DemoCmd.cmd)
public class HelloAction {
  /**
   * 示例 here 方法
   *
   * @param helloReq helloReq
   * @return HelloReq
   */
  @ActionMethod(DemoCmd.hello)
  public HelloReq hello(HelloReq helloReq, FlowContext flowContext) {
    HelloReq newHelloReq = new HelloReq();
    System.out.println("hello : " + helloReq.getName());
    newHelloReq.setName("hello:" + helloReq.getName() + ",uId:" + flowContext.getUserId());
    return newHelloReq;
  }
}