package cn.xwlin.rcgame.cons;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author xiang.liao
 * @create 2023/9/1
 * 赛事类型
 */
@Getter
@AllArgsConstructor
public enum EnumGameKind {
  CL3(1,"中冠"),
  CL2(2,"中乙"),
  CS1(3,"中甲"),
  CSL(4,"中超"),
  ASIA_CHAMPION(5,"亚冠"),
  CFA(6,"足协杯"),
          ;

  private int type;
  private String desc;

}
