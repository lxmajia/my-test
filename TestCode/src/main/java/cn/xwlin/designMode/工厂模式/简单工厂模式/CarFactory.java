package cn.xwlin.designMode.工厂模式.简单工厂模式;

public class CarFactory {
	public static Car getCar(String className) throws Exception {
		Car newInstance = (Car) Car.class.forName(className).newInstance();
		return newInstance;
	}
}
