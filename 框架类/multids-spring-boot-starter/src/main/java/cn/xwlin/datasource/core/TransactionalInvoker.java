package cn.xwlin.datasource.core;

import org.aspectj.lang.ProceedingJoinPoint;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Component
public class TransactionalInvoker {

  @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRES_NEW)
  public Object invokeWithNewTransaction(ProceedingJoinPoint joinPoint) throws Throwable  {
    return joinPoint.proceed();
  }

  @Transactional(rollbackFor = Exception.class, propagation = Propagation.NOT_SUPPORTED)
  public Object invokeWithoutTransaction(ProceedingJoinPoint joinPoint) throws Throwable  {
    return joinPoint.proceed();
  }
}