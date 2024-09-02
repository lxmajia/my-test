package cn.xwlin.object;


import com.alibaba.fastjson2.annotation.JSONField;

import java.util.Date;
import java.util.List;

public class EsOrderInfoIndexOBJ {
  @JSONField(name = "orderId")
  private Long orderId;
  @JSONField(name = "orderStatus")
  private Integer orderStatus;
  @JSONField(name = "userId")
  private Long userId;
  @JSONField(name = "contactName")
  private String contactName;
  @JSONField(name = "contactPhone")
  private String contactPhone;
  @JSONField(name = "createTime", format = "yyyy-MM-ddTHH:mm:ssZ")
  private Date createTime;
  @JSONField(name = "goodsList")
  private List<EsOrderInfoIndexOBJGoodsInfo> goodsList;

  public Long getOrderId() {
    return orderId;
  }

  public void setOrderId(Long orderId) {
    this.orderId = orderId;
  }

  public Integer getOrderStatus() {
    return orderStatus;
  }

  public void setOrderStatus(Integer orderStatus) {
    this.orderStatus = orderStatus;
  }

  public Long getUserId() {
    return userId;
  }

  public void setUserId(Long userId) {
    this.userId = userId;
  }

  public String getContactName() {
    return contactName;
  }

  public void setContactName(String contactName) {
    this.contactName = contactName;
  }

  public String getContactPhone() {
    return contactPhone;
  }

  public void setContactPhone(String contactPhone) {
    this.contactPhone = contactPhone;
  }

  public Date getCreateTime() {
    return createTime;
  }

  public void setCreateTime(Date createTime) {
    this.createTime = createTime;
  }

  public List<EsOrderInfoIndexOBJGoodsInfo> getGoodsList() {
    return goodsList;
  }

  public void setGoodsList(List<EsOrderInfoIndexOBJGoodsInfo> goodsList) {
    this.goodsList = goodsList;
  }
}
