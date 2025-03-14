package cn.xwlin.object;


import com.alibaba.fastjson2.annotation.JSONField;

import java.math.BigDecimal;

public class EsOrderInfoIndexOBJGoodsInfo {
  @JSONField(name = "goodsId")
  private Long goodsId;
  @JSONField(name = "goodsType")
  private Integer goodsType;
  @JSONField(name = "goodsUniqueId")
  private String goodsUniqueId;
  @JSONField(name = "goodsName")
  private String goodsName;
  @JSONField(name = "saleAmount")
  private BigDecimal saleAmount;
  @JSONField(name = "costAmount")
  private BigDecimal costAmount;

  public Long getGoodsId() {
    return goodsId;
  }

  public void setGoodsId(Long goodsId) {
    this.goodsId = goodsId;
  }

  public Integer getGoodsType() {
    return goodsType;
  }

  public void setGoodsType(Integer goodsType) {
    this.goodsType = goodsType;
  }

  public String getGoodsUniqueId() {
    return goodsUniqueId;
  }

  public void setGoodsUniqueId(String goodsUniqueId) {
    this.goodsUniqueId = goodsUniqueId;
  }

  public String getGoodsName() {
    return goodsName;
  }

  public void setGoodsName(String goodsName) {
    this.goodsName = goodsName;
  }

  public BigDecimal getSaleAmount() {
    return saleAmount;
  }

  public void setSaleAmount(BigDecimal saleAmount) {
    this.saleAmount = saleAmount;
  }

  public BigDecimal getCostAmount() {
    return costAmount;
  }

  public void setCostAmount(BigDecimal costAmount) {
    this.costAmount = costAmount;
  }
}
