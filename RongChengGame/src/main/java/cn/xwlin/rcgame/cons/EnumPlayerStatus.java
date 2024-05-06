package cn.xwlin.rcgame.cons;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author xiang.liao
 * @create 2023/9/1
 * 球员状态
 */
@Getter
@AllArgsConstructor
public enum EnumPlayerStatus {
  IN(1,"在队中"),
  LEAVE(2,"离队"),
  OFFLINE(3,"对中退役"),
          ;

  private int status;
  private String desc;

}
