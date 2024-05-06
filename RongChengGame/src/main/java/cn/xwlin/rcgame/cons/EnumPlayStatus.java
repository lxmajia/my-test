package cn.xwlin.rcgame.cons;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author xiang.liao
 * @create 2023/9/1
 * 比赛状态
 */
@Getter
@AllArgsConstructor
public enum EnumPlayStatus {
  NO_START(1, "未开始"),
  DOING(2, "进行中"),
  FINISHED(3, "结束"),
  DELAY(4, "延期"),
  ;

  private int status;
  private String desc;

}
