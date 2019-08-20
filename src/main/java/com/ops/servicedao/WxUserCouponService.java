package com.ops.servicedao;

import com.ops.entity.WxUserCoupon;

import java.util.List;

public interface WxUserCouponService {

    int add(Integer couponId, String[] userId);

    List<WxUserCoupon> findByUserId(String userId);

}
