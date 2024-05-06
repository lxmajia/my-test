package cn.xwlin.designMode.原型模式;

public class BmwCar extends Car {

    public BmwCar(){
        this.name = "BMW";
    }

    @Override
    void driver() {
        System.out.println("BMW RUN!!!");
    }
}
