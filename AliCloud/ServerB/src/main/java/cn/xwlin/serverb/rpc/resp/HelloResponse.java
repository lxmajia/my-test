package cn.xwlin.serverb.rpc.resp;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * @author Liao
 */
@Getter
@Setter
public class HelloResponse {
  private String helloInfo;
  private Date date;
}
