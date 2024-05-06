package cn.xwlin.dubbo.client.resp;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

/**
 * @author Liao
 */
@Getter
@Setter
public class HelloResponse implements Serializable {
    private String helloInfo;
    private Date date;
}
