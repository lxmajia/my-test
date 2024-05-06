package cn.xwlin.serverb.rpc.req;

import lombok.Getter;
import lombok.Setter;

/**
 * @author Liao
 */
@Getter
@Setter
public class HelloRequest {
  private String name;
  private Integer age;
}
