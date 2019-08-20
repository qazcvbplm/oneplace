package com.ops.dao;

import com.ops.entity.WxUserCoupon;

import java.util.List;

public interface WxUserCouponMapper {
    int insert(WxUserCoupon record);

    WxUserCoupon selectByPrimaryKey(Integer id);

    List<WxUserCoupon> findByUserId(String userId);

    int use(WxUserCoupon wuc);
}