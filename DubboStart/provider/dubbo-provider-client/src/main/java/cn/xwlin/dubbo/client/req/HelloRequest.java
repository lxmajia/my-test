package cn.xwlin.dubbo.client.req;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * @author Liao
 */
@Getter
@Setter
public class HelloRequest implements Serializable {
    private String name;
    private Integer age;
}
