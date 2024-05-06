package cn.xwlin.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

/**
 * @author MaJiaXueYuan.lx
 * @ClassName MyAop.java
 * @Description TODO
 * @createTime 2022年03月07日 21:20:00
 */
@Aspect
@Component
public class MyAop {

    @Pointcut(value = "@annotation(cn.xwlin.aop.Log)")
    public void pointCut() {
    }

    @Around("pointCut()")
    public Object around(ProceedingJoinPoint point) throws Throwable {
        MethodSignature signature = (MethodSignature) point.getSignature();
        Method method = signature.getMethod();
        Class<?> aClass = point.getTarget().getClass();
        Annotation[] annotations = aClass.getAnnotations();

        Method declaredMethod = point.getTarget().getClass().getMethod(signature.getName(), method.getParameterTypes());
        Log annotation = declaredMethod.getAnnotation(Log.class);
        String name = annotation.name();
        Object proceed = point.proceed();
        System.out.println("发生请求，URL：" + name + " req:{} resp:{} ms:{}");
        return proceed;
    }


}
