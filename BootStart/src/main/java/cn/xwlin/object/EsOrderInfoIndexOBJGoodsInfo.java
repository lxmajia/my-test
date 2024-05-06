package cn.xwlin.object;


import com.alibaba.fastjson2.annotation.JSONField;

import java.math.BigDecimal;
import java.util.Date;

public class EsOrderInfoIndexOBJGoodsInfo {
    @JSONField(name = "orderId")
    private Long orderId;
    @JSONField(name = "goodsId")
    private Long goodsId;
    @JSONField(name = "goodsName")
    private String goodsName;
    @JSONField(name = "goodsType")
    private Integer goodsType;
    @JSONField(name = "saleAmount")
    private BigDecimal saleAmount;
    @JSONField(name = "costAmount")
    private BigDecimal costAmount;
    @JSONField(name = "createTime")
    private Date createTime;

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Long getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Long goodsId) {
        this.goodsId = goodsId;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public Integer getGoodsType() {
        return goodsType;
    }

    public void setGoodsType(Integer goodsType) {
        this.goodsType = goodsType;
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

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
