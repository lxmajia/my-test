package cn.xwlin.designMode.单例模式.饿汉式;

/**
 * 饿汉式
 * 
 * @author LIAO
 *
 */
public class Singleton {
	private static Singleton instans = new Singleton();

	private Singleton() {
	}

	public static Singleton getInstans() {
		return instans;
	}

}
