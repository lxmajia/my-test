package cn.xwlin.advice;

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

  @org.springframework.web.bind.annotation.ExceptionHandler(MethodArgumentNotValidException.class)
  public String handle(MethodArgumentNotValidException e) {
    e.printStackTrace();
    return e.getBindingResult().getFieldError().getDefaultMessage();
  }

  @org.springframework.web.bind.annotation.ExceptionHandler(BindException.class)
  public String handle(BindException e) {
    e.printStackTrace();
    return e.getBindingResult().getFieldError().getDefaultMessage();
  }

  @org.springframework.web.bind.annotation.ExceptionHandler(ConstraintViolationException.class)
  public String handle(ConstraintViolationException e) {
    e.printStackTrace();
    StringBuffer sb = new StringBuffer();
    for (ConstraintViolation<?> violation : e.getConstraintViolations()) {
      sb.append(violation.getMessage());
    }
    return sb.toString();
  }
}