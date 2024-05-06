package cn.xwlin.endpoint;

import org.springframework.boot.actuate.endpoint.annotation.Endpoint;
import org.springframework.boot.actuate.endpoint.annotation.ReadOperation;
import org.springframework.boot.actuate.endpoint.annotation.Selector;
import org.springframework.boot.actuate.info.Info;
import org.springframework.stereotype.Component;

@Component
@Endpoint(id = "myEndpoint")
public class MyHealthEndPoint {

    /**
     * @Selector 获取传递的参数
     * 添加@ReadOperation，@ WritOperation或@DeleteOperation注释后，该方法将通过JMX自动公开，并且在Web应用程序中也通过HTTP公开
     */
    @ReadOperation
    public Info getByName(@Selector String name) {
        Info.Builder builder = new Info.Builder();
        builder.withDetail("name", name);
        builder.withDetail("author", "laoliao");
        return builder.build();
    }
}