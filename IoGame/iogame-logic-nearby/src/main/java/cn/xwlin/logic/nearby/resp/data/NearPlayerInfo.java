package cn.xwlin.logic.nearby.resp.data;

import java.math.BigDecimal;

/**
 * @author xiang.liao
 * @create 2024/5/17
 * 用户信息
 */
public class NearPlayerInfo {
  /**
   * 玩家ID
   */
  private Long userId;
  /**
   * 经度
   */
  private BigDecimal longitude;
  /**
   * 纬度
   */
  private BigDecimal latitude;
  /**
   * 距离
   */
  private BigDecimal distance;
  /**
   * 玩家类型
   */
  private Integer userType;
  /**
   * 生命值
   */
  private Long bloodAmount;
  /**
   * 剩余生命值
   */
  private Long bloodRemain;
  /**
   * 技能值（蓝量）
   */
  private Long skillAmount;
  /**
   * 剩余技能值（蓝量）
   */
  private Long skillRemain;
  /**
   * 玩家性别
   */
  private Integer userSex;
  /**
   * 玩家名
   */
  private String playerName;
  /**
   * 职位图标等
   */
  private Integer tagIconId;
  /**
   * 职位名称
   */
  private String tagName;
  /**
   * 玩家属性信息
   */
  private NearPlayerAttrInfo nearPlayerAttrInfo;

  public BigDecimal getDistance() {
    return distance;
  }

  public void setDistance(BigDecimal distance) {
    this.distance = distance.setScale(2, BigDecimal.ROUND_HALF_UP);
  }

  public BigDecimal getLongitude() {
    return longitude;
  }

  public void setLongitude(BigDecimal longitude) {
    this.longitude = longitude.setScale(6, BigDecimal.ROUND_HALF_UP);
  }

  public BigDecimal getLatitude() {
    return latitude;
  }

  public void setLatitude(BigDecimal latitude) {
    this.latitude = latitude.setScale(6, BigDecimal.ROUND_HALF_UP);;
  }

  public Long getBloodAmount() {
    return bloodAmount;
  }

  public void setBloodAmount(Long bloodAmount) {
    this.bloodAmount = bloodAmount;
  }

  public Long getBloodRemain() {
    return bloodRemain;
  }

  public void setBloodRemain(Long bloodRemain) {
    this.bloodRemain = bloodRemain;
  }

  public Long getSkillAmount() {
    return skillAmount;
  }

  public void setSkillAmount(Long skillAmount) {
    this.skillAmount = skillAmount;
  }

  public Long getSkillRemain() {
    return skillRemain;
  }

  public void setSkillRemain(Long skillRemain) {
    this.skillRemain = skillRemain;
  }

  public Long getUserId() {
    return userId;
  }

  public void setUserId(Long userId) {
    this.userId = userId;
  }

  public Integer getUserType() {
    return userType;
  }

  public void setUserType(Integer userType) {
    this.userType = userType;
  }

  public Integer getUserSex() {
    return userSex;
  }

  public void setUserSex(Integer userSex) {
    this.userSex = userSex;
  }

  public String getPlayerName() {
    return playerName;
  }

  public void setPlayerName(String playerName) {
    this.playerName = playerName;
  }

  public Integer getTagIconId() {
    return tagIconId;
  }

  public void setTagIconId(Integer tagIconId) {
    this.tagIconId = tagIconId;
  }

  public String getTagName() {
    return tagName;
  }

  public void setTagName(String tagName) {
    this.tagName = tagName;
  }

  public NearPlayerAttrInfo getNearPlayerAttrInfo() {
    return nearPlayerAttrInfo;
  }

  public void setNearPlayerAttrInfo(NearPlayerAttrInfo nearPlayerAttrInfo) {
    this.nearPlayerAttrInfo = nearPlayerAttrInfo;
  }
}
