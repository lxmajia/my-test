package cn.xwlin.rcgame.advice;

import cn.dev33.satoken.exception.NotLoginException;
import cn.xwlin.rcgame.controller.response.HttpResp;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

/**
 * 参数校验异常包括MethodArgumentNotValidException、BindException、ConstraintViolationException
 */
@RestControllerAdvice
public class ExceptionHandler {
  @org.springframework.web.bind.annotation.ExceptionHandler(NotLoginException.class)
  public HttpResp handle(NotLoginException e) {
    return HttpResp.fail(401, "未登录");
  }
}