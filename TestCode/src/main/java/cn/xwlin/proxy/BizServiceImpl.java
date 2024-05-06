package cn.xwlin.proxy;

/**
 * @author MaJiaXueYuan.lx
 * @ClassName BizServiceImpl.java
 * @Description TODO
 * @createTime 2022年03月07日 22:22:00
 */
public class BizServiceImpl implements BizService {
    @Override
    public String biz() {
        System.out.println("执行业务代码");
        return "执行业务代码";
    }
}
