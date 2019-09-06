package com.ops.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.ops.util.MQO;
import org.hibernate.validator.constraints.NotEmpty;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class Orders {

    private String id;
    @NotEmpty
    private String userid;

    private String shopid;

    private BigDecimal totalprice;

    private String status;

    private String type;

    private BigDecimal couponprice;

    private Integer couponid;

    @TableField(exist = false)
    private MQO mqo = new MQO();

    private Date createtime;

    private String remark;

    private String addressName;

    private String addressPhone;

    private String address;

    private String shopName;

    @TableField(exist = false)
    private List<OrderProduct> op;

    private String prepareId;

    public String getPrepareId() {
        return prepareId;
    }

    public void setPrepareId(String prepareId) {
        this.prepareId = prepareId;
    }

    public List<OrderProduct> getOp() {
        return op;
    }

    public void setOp(List<OrderProduct> op) {
        this.op = op;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public String getAddressName() {
        return addressName;
    }

    public void setAddressName(String addressName) {
        this.addressName = addressName;
    }

    public String getAddressPhone() {
        return addressPhone;
    }

    public void setAddressPhone(String addressPhone) {
        this.addressPhone = addressPhone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Orders() {
        super();
    }

    public Orders set(String orderId, BigDecimal total) {
        this.id = orderId;
        this.totalprice = total;
        this.status = "待付款";
        if (this.shopid == null) {
            this.shopid = "";
        }
        this.couponprice = new BigDecimal(0);
        if (couponid == null)
            this.couponid = 0;
        return this;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public MQO getMqo() {
        return mqo;
    }

    public void setMqo(MQO mqo) {
        this.mqo = mqo;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid == null ? null : userid.trim();
    }

    public String getShopid() {
        return shopid;
    }

    public void setShopid(String shopid) {
        this.shopid = shopid == null ? null : shopid.trim();
    }

    public BigDecimal getTotalprice() {
        return totalprice;
    }

    public void setTotalprice(BigDecimal totalprice) {
        this.totalprice = totalprice;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    public BigDecimal getCouponprice() {
        return couponprice;
    }

    public void setCouponprice(BigDecimal couponprice) {
        this.couponprice = couponprice;
    }

    public Integer getCouponid() {
        return couponid;
    }

    public void setCouponid(Integer couponid) {
        this.couponid = couponid;
    }


}