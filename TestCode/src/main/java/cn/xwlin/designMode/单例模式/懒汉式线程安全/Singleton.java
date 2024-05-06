package cn.xwlin.designMode.单例模式.懒汉式线程安全;

/**
 * 饿汉式
 * 
 * @author LIAO
 *
 */
public class Singleton {
	private static Singleton instans = null;

	private Singleton() {
	}

	public static Singleton getInstans() {
		synchronized (Singleton.class) {
			if (instans == null) {
				System.out.println("线程安全，初始化");
				instans = new Singleton();
			}
		}
		return instans;
	}

}
