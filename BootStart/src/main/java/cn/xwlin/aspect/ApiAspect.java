//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package cn.xwlin.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;

@Aspect
@Order(-1)
public class ApiAspect {
    private ApiInvokeHandlerImpl apiInvokeHandler;
    private static final Logger logger = LoggerFactory.getLogger(ApiAspect.class);

    public ApiAspect(ApiInvokeHandlerImpl apiInvokeHandler) {
        this.apiInvokeHandler = apiInvokeHandler;
    }

    @Around("execution(@org.springframework.web.bind.annotation.RequestMapping public * cn.xwlin..*.*(..))")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        Object[] objects = joinPoint.getArgs();
        Object result = null;
        Object response = null;

        Object var7;
        try {
            this.apiInvokeHandler.initContext(methodSignature.getName(), methodSignature.getDeclaringTypeName(), objects);
            if (response == null) {
                result = joinPoint.proceed();
                return result;
            }

            var7 = response;
        } catch (Throwable var12) {
            Object var8 = response;
            return var8;
        } finally {
        }
        return var7;
    }
}
