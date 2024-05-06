package cn.xwlin.designMode.工厂模式.抽象工厂模式;

public class BLUEBMW_CARFACTORY implements CarFactory {

	@Override
	public Car getCar() {
		return new BMWCar();
	}

	@Override
	public Color getColor() {
		return new BlueColor();
	}
}
