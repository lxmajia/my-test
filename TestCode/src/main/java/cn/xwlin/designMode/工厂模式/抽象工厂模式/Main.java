package cn.xwlin.designMode.工厂模式.抽象工厂模式;
/**
 * 以品牌颜色为例，汽车由厂商和颜色color组成，实现蓝色宝马，红色奔驰车型
 * 通过抽象工厂实现车型自由切换例子
 * 抽象工厂指定了产品组合形式，具体的工厂产生具体的产品，抽象工厂适用于多个产品相互组合的情况。
 * @author LIAO
 *
 */
public class Main {
	// 用来生产不同产品族(由不同产品组合成的一套产品)的全部产品，对于增加新的产品，无能为力；支持增加产品族。
	public static void main(String[] args) {
		BLUEBMW_CARFACTORY bluebmw_CARFACTORY = new BLUEBMW_CARFACTORY();
		REDBENZ_CARFACTORY redbenz_CARFACTORY = new REDBENZ_CARFACTORY();

		CarModel model = new CarModel();
		model.setCarFactory(bluebmw_CARFACTORY);
		model.showModel();
		System.out.println("切换Factory");
		model.setCarFactory(redbenz_CARFACTORY);
		model.showModel();
	}
}
