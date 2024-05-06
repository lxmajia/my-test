package cn.xwlin.logger;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LoggerUtils {
  private static final Logger controllerLogger = LoggerFactory.getLogger(
          "controllerRequestAppender");

  public static Logger getControllerLogger() {
    return controllerLogger;
  }
}
