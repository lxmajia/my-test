package cn.xwlin.nearBy;

/**
 * @author xiang.liao
 * @create 2024/5/17
 * 用户属性
 */
public class NearPlayerAttrInfo {
  /**
   * 武器类型
   */
  private Integer weaponType;
  /**
   * 武器级别
   */
  private Integer weaponLevel;

  public Integer getWeaponType() {
    return weaponType;
  }

  public void setWeaponType(Integer weaponType) {
    this.weaponType = weaponType;
  }

  public Integer getWeaponLevel() {
    return weaponLevel;
  }

  public void setWeaponLevel(Integer weaponLevel) {
    this.weaponLevel = weaponLevel;
  }
}
