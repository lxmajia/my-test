package cn.xwlin.common.action;

/**
 * 路由文件信息，养成良好的习惯；使用接口文件来管理我们 action 的路由。
 * cmd和subCmd都只支持1-127
 */
public interface CmdConst {

  interface HelloCmd {
    int cmd = 1;

    interface SubCmd {
      int hello = 1;
    }
  }
  interface NearbyCmd {
    int cmd = 2;

    interface SubCmd {
      int nearPlayer = 1;
    }
  }
}
