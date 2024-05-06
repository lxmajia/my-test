package cn.xwlin.aop;

import lombok.Getter;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author MaJiaXueYuan.lx
 * @ClassName Log.java
 * @Description TODO
 * @createTime 2022年03月07日 21:20:00
 */
@Retention(value = RetentionPolicy.RUNTIME)
@Target(value = ElementType.METHOD)
public @interface Log {
    String name();
}
