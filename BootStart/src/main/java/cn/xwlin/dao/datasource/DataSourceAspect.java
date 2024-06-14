package cn.xwlin.dao.datasource;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionSynchronizationManager;

@Aspect
@Component
public class DataSourceAspect {

  @Autowired
  private TransactionalInvoker transactionalInvoker;

  @Pointcut("@annotation(cn.xwlin.dao.datasource.DS) || @within(cn.xwlin.dao.datasource.DS)")
  public void dataSourcePointCut() {

  }

  @Around("dataSourcePointCut()")
  public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
    MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
    String oldSourceKey = DatasourceSwtich.getDsName();
    String currentSourceKey = getDSAnnotationValue(joinPoint).toUpperCase();

    boolean suspendExistTransaction = false;
    boolean useTransactionalInvoker = false;
    if (oldSourceKey != null && !oldSourceKey.equals(currentSourceKey)) {
      //以下代码支持多数据源嵌套，必要时可以使用
      if (TransactionSynchronizationManager.isSynchronizationActive()) {
        //Synchronization范围内会使用同一个connection，所以按嵌套数据源Transaction定义分两种情况
        //1.注解明确定义开启新事务或不加入事务，则直接调用，spring管理事务
        //2.无注解或注解定义继承原事务，因切换数据源，所以需要
        //  2.1.无注解，则排除在事务外
        //  2.2.有注解，则强制使用新事务
        Transactional transactional = methodSignature.getMethod().getAnnotation(Transactional.class);
        //切换数据源，未明确定义使用新建事务或不使用事务，则需要使用TransactionInvoker
        if (!isExplicitUseNewOrNotUseTransaction(transactional)) {
          useTransactionalInvoker = true;
          if (transactional == null) {
            suspendExistTransaction = true;
          }
        }
      }
    }

    DatasourceSwtich.setDsName(currentSourceKey);
    try {
      if (useTransactionalInvoker) {
        if (suspendExistTransaction) {
          return transactionalInvoker.invokeWithoutTransaction(joinPoint);
        } else {
          return transactionalInvoker.invokeWithNewTransaction(joinPoint);
        }
      } else {
        return joinPoint.proceed();
      }
    } finally {
      DatasourceSwtich.setDsName(oldSourceKey);
      if (DatasourceSwtich.getDsName() == null) {
        DatasourceSwtich.removeContextKey();
      }
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

  private boolean isExplicitUseNewOrNotUseTransaction(Transactional transactional) {
    if (transactional != null) {
      Propagation propagation = transactional.propagation();
      if (propagation == Propagation.REQUIRES_NEW || propagation == Propagation.NOT_SUPPORTED || propagation == Propagation.NEVER) {
        return true;
      }
    }
    return false;
  }
}
