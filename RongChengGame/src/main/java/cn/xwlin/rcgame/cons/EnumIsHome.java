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
public enum EnumIsHome {
  YES(1, "主场"),
  NO(2, "客场"),
  FAIR(0, "中立场")
  ;

  private int status;
  private String desc;

}
