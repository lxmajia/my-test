package cn.xwlin.configcenter.excep;


public class WlinConfigException extends RuntimeException {

  public WlinConfigException()
  {
    super();
  }

  public WlinConfigException(String message) {
    super(message);
  }

  public WlinConfigException(String message, Throwable cause) {
    super(message, cause);
  }

  public WlinConfigException(Throwable cause) {
    super(cause);
  }

  protected WlinConfigException(String message, Throwable cause,
                                boolean enableSuppression,
                                boolean writableStackTrace) {
    super(message, cause, enableSuppression, writableStackTrace);
  }

}
