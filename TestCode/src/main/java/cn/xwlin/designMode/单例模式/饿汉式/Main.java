package cn.xwlin.designMode.单例模式.饿汉式;

public class Main {
	public static void main(String[] args) {
		Runnable runnable = new Runnable() {
			@Override
			public void run() {
				Singleton instant1 = Singleton.getInstans();
				System.out.println(instant1);
			}
		};

		for (int i = 0; i < 10; i++) {
			new Thread(runnable).start();
		}
	}
}
