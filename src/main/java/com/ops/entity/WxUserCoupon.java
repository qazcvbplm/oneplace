package com.ops.entity;

import java.util.Date;

public class WxUserCoupon {
    private Integer id;

    private String userid;

    private String orderid;

    private Integer couponid;

    private Date createtime;

    private Long outtime;

    private Coupon coupon;


    public Coupon getCoupon() {
        return coupon;
    }

    public void setCoupon(Coupon coupon) {
        this.coupon = coupon;
    }

    public WxUserCoupon() {
        super();
    }

    public WxUserCoupon(String userid, Integer couponid, Long outtime) {
        super();
        this.userid = userid;
        this.couponid = couponid;
        this.outtime = outtime;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid == null ? null : userid.trim();
    }

    public String getOrderid() {
        return orderid;
    }

    public void setOrderid(String orderid) {
        this.orderid = orderid == null ? null : orderid.trim();
    }

    public Integer getCouponid() {
        return couponid;
    }

    public void setCouponid(Integer couponid) {
        this.couponid = couponid;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public Long getOuttime() {
        return outtime;
    }

    public void setOuttime(Long outtime) {
        this.outtime = outtime;
    }
}