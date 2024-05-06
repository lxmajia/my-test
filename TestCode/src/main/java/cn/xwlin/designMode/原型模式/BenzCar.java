package cn.xwlin.designMode.原型模式;

public class BenzCar extends Car {

    public BenzCar(){
        this.name = "BENZ";
    }

    @Override
    void driver() {
        System.out.println("BENZ RUN!!!");
    }
}
