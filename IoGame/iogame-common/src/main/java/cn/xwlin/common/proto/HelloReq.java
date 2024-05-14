package cn.xwlin.common.proto;

import lombok.AccessLevel;
import lombok.ToString;
import lombok.experimental.FieldDefaults;

/**
 * @author 渔民小镇
 * @date 2022-12-04
 */
@ToString
@FieldDefaults(level = AccessLevel.PUBLIC)
public class HelloReq {
     String name;
}