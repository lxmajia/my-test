package cn.xwlin.common.proto;

import lombok.AccessLevel;
import lombok.ToString;
import lombok.experimental.FieldDefaults;

/**
 * @author 渔民小镇
 * @date 2022-12-04
 */
@ToString
public class HelloReq {
     private String name;

     public String getName() {
          return name;
     }

     public void setName(String name) {
          this.name = name;
     }
}