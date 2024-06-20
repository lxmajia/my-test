package cn.xwlin.log.aop;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

/**
 * @author liao.xiang
 * @date 2020/8/24.21:56
 */
@Slf4j
@Aspect
@Component
public class MethodLogAspect {

    /**
     * 监听注解MethodLog，打印请求和返回日志
     */
    @Around(value = "@annotation(cn.xwlin.log.anno.MethodLog)")
    public Object logAround(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        // 这里可能会出现异常的情况，其实这个结果跟动态代理是一样的。
        String className = proceedingJoinPoint.getTarget().getClass().getName();
        MethodSignature signature = (MethodSignature) proceedingJoinPoint.getSignature();
        String methodName = signature.getMethod().getName();
        // 请求的参数
        Object[] args = proceedingJoinPoint.getArgs();
        // 将参数所在的数组转换成json
        String params = args == null ? "" : args.length == 1 ? JSON.toJSONString(args[0]) : JSON.toJSONString(args);
        Object result = proceedingJoinPoint.proceed(args);
        log.info("[方法:{}.{}],req:{},resp:{}", className, methodName, params, JSON.toJSONString(result));
        return result;
    }
}
