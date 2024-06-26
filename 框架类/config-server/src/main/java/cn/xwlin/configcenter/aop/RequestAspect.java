package cn.xwlin.configcenter.aop;

import cn.xwlin.configcenter.vo.resp.HttpResp;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class RequestAspect {

  @Pointcut("@annotation(org.springframework.web.bind.annotation.RequestMapping)")
  public void requestTime() {

  }

  @Around("requestTime()")
  public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
    long sTime = System.currentTimeMillis();
    try {
      Object proceed = joinPoint.proceed();
      long eTime = System.currentTimeMillis();
      if (proceed instanceof HttpResp) {
        HttpResp resp = (HttpResp) proceed;
        resp.setServerTime(eTime - sTime);
        return resp;
      }
      return proceed;
    } finally {
    }
  }
}
