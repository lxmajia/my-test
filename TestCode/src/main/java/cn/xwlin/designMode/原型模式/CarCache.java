package cn.xwlin.designMode.原型模式;


import java.util.Hashtable;

public class CarCache {

    private static Hashtable<String, Car> carMap = new Hashtable<String, Car>();

    public static Car getCar(String carId) throws CloneNotSupportedException {
        Car cachedCar = carMap.get(carId);
        return (Car) cachedCar.clone();
    }

    // 模拟第一次从数据库加载
    public static void loadCache() {
        BenzCar benz = new BenzCar();
        benz.setId("1");
        carMap.put(benz.getId(),benz);

        BmwCar bmw = new BmwCar();
        bmw.setId("2");
        carMap.put(bmw.getId(),bmw);
    }



}
