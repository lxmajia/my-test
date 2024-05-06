package cn.xwlin.object;


import com.alibaba.fastjson2.annotation.JSONField;

import java.util.Date;
import java.util.List;

public class EsOrderInfoIndexOBJ {
    @JSONField(name = "orderGoods")
    private Long orderId;
    @JSONField(name = "orderGoods")
    private Integer orderType;
    @JSONField(name = "orderGoods")
    private Integer orderFrom;
    @JSONField(name = "orderGoods")
    private String transNo;
    @JSONField(name = "orderGoods")
    private String receiveName;
    @JSONField(name = "orderGoods")
    private String receiveMobile;
    @JSONField(name = "orderGoods")
    private Long shopId;
    @JSONField(name = "orderGoods")
    private Integer orderStatus;
    @JSONField(name = "orderGoods")
    private Date createTime;
    @JSONField(name = "orderGoods")
    private List<EsOrderInfoIndexOBJGoodsInfo> orderGoods;

    public String getId() {
        return String.valueOf(orderId);
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Integer getOrderType() {
        return orderType;
    }

    public void setOrderType(Integer orderType) {
        this.orderType = orderType;
    }

    public Integer getOrderFrom() {
        return orderFrom;
    }

    public void setOrderFrom(Integer orderFrom) {
        this.orderFrom = orderFrom;
    }

    public String getTransNo() {
        return transNo;
    }

    public void setTransNo(String transNo) {
        this.transNo = transNo;
    }

    public String getReceiveName() {
        return receiveName;
    }

    public void setReceiveName(String receiveName) {
        this.receiveName = receiveName;
    }

    public String getReceiveMobile() {
        return receiveMobile;
    }

    public void setReceiveMobile(String receiveMobile) {
        this.receiveMobile = receiveMobile;
    }

    public Long getShopId() {
        return shopId;
    }

    public void setShopId(Long shopId) {
        this.shopId = shopId;
    }

    public Integer getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(Integer orderStatus) {
        this.orderStatus = orderStatus;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public List<EsOrderInfoIndexOBJGoodsInfo> getOrderGoods() {
        return orderGoods;
    }

    public void setOrderGoods(List<EsOrderInfoIndexOBJGoodsInfo> orderGoods) {
        this.orderGoods = orderGoods;
    }
}
