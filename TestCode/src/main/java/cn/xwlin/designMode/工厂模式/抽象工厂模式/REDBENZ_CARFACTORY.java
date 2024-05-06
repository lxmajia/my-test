package cn.xwlin.designMode.工厂模式.抽象工厂模式;

public class REDBENZ_CARFACTORY implements CarFactory {

	@Override
	public Car getCar() {
		return new BENZCar();
	}

	@Override
	public Color getColor() {
		return new RedColor();
	}
}
