package cn.xwlin.dao.datasource;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;

@Aspect
public class DataSourceAspect {

    @Pointcut("@annotation(cn.xwlin.dao.datasource.DS) || @within(cn.xwlin.dao.datasource.DS)")
    public void dataSourcePointCut() {

    }

    @Around("dataSourcePointCut()")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        String dsKey = getDSAnnotationValue(joinPoint).toUpperCase();
        DatasourceSwtich.setDsName(dsKey);
        try {
            return joinPoint.proceed();
        } finally {
            DatasourceSwtich.removeContextKey();
        }
    }

    /**
     * 根据类或方法获取数据源注解
     */
    private String getDSAnnotationValue(ProceedingJoinPoint joinPoint) {
        // 优先取方法上的注解，如果方法上没有注解的话，那么使用类上面的，类上面没有的话，使用方法上的
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        DS methodAnno = methodSignature.getMethod().getAnnotation(DS.class);
        if (methodAnno != null) {
            return methodAnno.value();
        }
        Class<?> targetClass = joinPoint.getTarget().getClass();
        DS classAnno = targetClass.getAnnotation(DS.class);
        if (classAnno != null) {
            return classAnno.value();
        }
        return "";
    }
}
