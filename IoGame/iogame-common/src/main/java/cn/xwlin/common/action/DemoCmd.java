package cn.xwlin.common.action;

// 路由文件信息，养成良好的习惯；使用接口文件来管理我们 action 的路由。
public interface DemoCmd {
    /** 主路由 */
    int cmd = 1;
    /** 子路由 hello */
    int hello = 1;
}
