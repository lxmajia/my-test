package cn.xwlin.servera.req;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * @author Liao
 */
@Getter
@Setter
public class HelloRequest {
  private String name;
  private Integer age;
}
