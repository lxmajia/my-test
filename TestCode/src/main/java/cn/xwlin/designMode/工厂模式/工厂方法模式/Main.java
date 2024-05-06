package cn.xwlin.designMode.工厂模式.工厂方法模式;

/**
 * 工厂方法中也只包含一个抽象产品类，抽象产品类可以派生出多个具体产品类。
 * 工厂方法定义一个用于创建产品的接口，让子类决定实例化哪一个类，使得类的实例化延迟到子类。
 * 
 * @author LIAO
 *
 */
public class Main {
	// 用来生产同一等级结构中的固定产品，支持增加任意产品。
	public static void main(String[] args) {
		CarFactory bmwFactory = new BmwCarFactory();
		CarFactory benzFactory = new BenzCarFactory();

		Car bmw = bmwFactory.getCar();
		Car benz = benzFactory.getCar();

		bmw.run();
		benz.run();
	}
}
