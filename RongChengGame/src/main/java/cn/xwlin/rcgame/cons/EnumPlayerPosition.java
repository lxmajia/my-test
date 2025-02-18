package cn.xwlin.rcgame.cons;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author xiang.liao
 * @create 2023/9/1
 * 球员位置
 */
@Getter
@AllArgsConstructor
public enum EnumPlayerPosition {
  GK("门将"),
  CB("中后卫"),
  RB("右后卫"),
  LB("左后卫"),
  CDM("后腰"),
  CM("中场"),
  RM("右前卫"),
  LM("左前卫"),
  CF("中前锋"),
  RF("右前锋"),
  LF("左前锋"),
  ST("中锋"),
  RS("右锋"),
  LS("左锋"),

          ;

  private String desc;

}
