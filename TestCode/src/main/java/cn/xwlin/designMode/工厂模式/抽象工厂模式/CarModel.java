package cn.xwlin.designMode.工厂模式.抽象工厂模式;

public class CarModel {
	private CarFactory carFactory;

	public void setCarFactory(CarFactory carFactory) {
		this.carFactory = carFactory;
	}

	public void showModel() {
		carFactory.getColor().write();
		carFactory.getCar().run();
	}
}
