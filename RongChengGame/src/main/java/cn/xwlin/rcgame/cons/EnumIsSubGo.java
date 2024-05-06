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
public enum EnumIsSubGo {
  YES(1, "替补上场"),
  NO(0, "替补未上场")
  ;

  private int status;
  private String desc;

}
