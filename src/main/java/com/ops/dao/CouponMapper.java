package com.ops.dao;

import com.ops.entity.Coupon;

import java.util.List;

public interface CouponMapper {
    int insert(Coupon record);

    int insertSelective(Coupon record);

    Coupon selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Coupon record);

    int updateByPrimaryKey(Coupon record);

    List<Coupon> find(Coupon coupon);
}