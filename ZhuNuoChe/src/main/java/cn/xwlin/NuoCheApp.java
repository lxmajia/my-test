package cn.xwlin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class NuoCheApp {
    public static void main(String[] args) {
        ConfigurableApplicationContext run = SpringApplication.run(NuoCheApp.class, args);
//        WxServer bean = run.getBean(WxServer.class);
//
//        try {
//            bean.sendNuoCheWxMsg(WxAppInfo.MY_OPEN_ID, "index/index", "123", "川A·7B5T4", "请麻烦挪一下车");
//        } catch (WxErrorException e) {
//            e.printStackTrace();
//            throw new RuntimeException(e);
//        }

    }
}
