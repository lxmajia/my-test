package cn.xwlin.logic.action;

import cn.xwlin.common.action.CmdConst;
import cn.xwlin.common.proto.HelloReq;
import com.iohao.game.action.skeleton.annotation.ActionController;
import com.iohao.game.action.skeleton.annotation.ActionMethod;
import com.iohao.game.action.skeleton.core.flow.FlowContext;

@ActionController(CmdConst.LoginCmd.cmd)
public class LoginAction {
  /**
   * 示例 here 方法
   *
   * @param helloReq helloReq
   * @return HelloReq
   */
  @ActionMethod(CmdConst.LoginCmd.SubCmd.login)
  public HelloReq hello(HelloReq helloReq, FlowContext flowContext) {
    HelloReq newHelloReq = new HelloReq();
    System.out.println("hello : " + helloReq.getName());
    newHelloReq.setName("hello:" + helloReq.getName() + ",uId:" + flowContext.getUserId());
    return newHelloReq;
  }
}