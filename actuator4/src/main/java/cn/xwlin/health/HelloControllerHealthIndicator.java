package cn.xwlin.health;

import cn.xwlin.controller.HelloController;
import org.springframework.boot.actuate.health.AbstractHealthIndicator;
import org.springframework.boot.actuate.health.Health;
import org.springframework.stereotype.Component;

@Component
public class HelloControllerHealthIndicator extends AbstractHealthIndicator {

    private HelloController helloController;

    public HelloControllerHealthIndicator(HelloController helloController) {
        this.helloController = helloController;
    }

    @Override
    protected void doHealthCheck(Health.Builder builder) throws Exception {
        // Use the builder to build the health status details that should be reported.
        // If you throw an exception, the status will be DOWN with the exception message.
        String s = helloController.getClass().toString();
        String s1 = helloController.toString();
        builder.up()
                .withDetail("bean", s)
                .withDetail("author", "LiaoXiang").withDetail("instance", s1);
    }
}