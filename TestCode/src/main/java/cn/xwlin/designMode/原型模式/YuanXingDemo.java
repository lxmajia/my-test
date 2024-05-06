package cn.xwlin.designMode.原型模式;

/**
 * 原型模式用于利用原来的对象直接克隆出来对象，再有些创建对象需要浪费资源时使用
 */
public class YuanXingDemo {
    public static void main(String[] args) throws Exception{
        CarCache.loadCache();

        Car car1 = (Car)CarCache.getCar("1");
        Car car2 = (Car)CarCache.getCar("1");

        car1.driver();
        car2.driver();

        System.out.println(car1);
        System.out.println(car2);
    }
}
