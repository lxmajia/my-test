package cn.xwlin.designMode.工厂模式.简单工厂模式;

/**
 * 又叫静态工厂模式，简单工厂只包括一个抽象产品类（该类可以是接口，也可以是具体的类） 所有需要的产品类都是该抽象产品类的子类。
 * 简单工厂模式中工厂为具体产品工厂，产品为抽象产品，由工厂实例创建产品实例
 * 
 * @author LIAO
 *
 */
public class Main {
	// 用来生产同一等级结构中的任意产品，对于增加新的产品，无能为力。
	public static void main(String[] args) throws Exception {
		Car car = CarFactory.getCar("per.lx.工厂模式.简单工厂模式.BMWCar");
		Car car2 = CarFactory.getCar("per.lx.工厂模式.简单工厂模式.BENZCar");
		car.run();
		car2.run();
	}
}
