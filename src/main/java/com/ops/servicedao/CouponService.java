package com.ops.servicedao;

import com.ops.entity.Coupon;

import java.util.List;

public interface CouponService {

    void add(Coupon coupon);

    List<Coupon> find(Coupon coupon);

}
