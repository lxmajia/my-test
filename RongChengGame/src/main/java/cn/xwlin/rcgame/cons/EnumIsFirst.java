package cn.xwlin.rcgame.cons;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author xiang.liao
 * @create 2023/9/1
 * 是否主场
 */
@Getter
@AllArgsConstructor
public enum EnumIsFirst {
  YES(1, "首发"),
  NO(0, "替补")
  ;

  private int status;
  private String desc;

}
