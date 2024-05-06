package cn.xwlin.designMode.工厂模式.工厂方法模式;

public class BmwCarFactory implements CarFactory {

	@Override
	public Car getCar() {
		return new BMWCar();
	}

}
