package cn.xwlin.configcenter.consts;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author xiang.liao
 * @create 2025/3/14
 */
@AllArgsConstructor
@Getter
public enum EnumAppConfigType {
  JSON("JSON"),
  TEXT("TEXT"),
  ;

  private final String type;
}
