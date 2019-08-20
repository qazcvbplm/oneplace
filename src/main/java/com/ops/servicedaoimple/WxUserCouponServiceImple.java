package com.ops.servicedaoimple;

import com.ops.dao.CouponMapper;
import com.ops.dao.WxUserCouponMapper;
import com.ops.entity.Coupon;
import com.ops.entity.WxUserCoupon;
import com.ops.servicedao.WxUserCouponService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class WxUserCouponServiceImple implements WxUserCouponService {

    @Autowired
    private WxUserCouponMapper wxUserCouponMapper;
    @Autowired
    private CouponMapper couponMapper;

    private static final long day = 24 * 60 * 60 * 1000;

    @Override
    public int add(Integer couponId, String[] userId) {
        Coupon coupon = couponMapper.selectByPrimaryKey(couponId);
        for (String temp : userId) {
            WxUserCoupon wxUserCoupon = new WxUserCoupon(temp, coupon.getId(), System.currentTimeMillis() + (coupon.getOutDay() * day));
            wxUserCouponMapper.insert(wxUserCoupon);
        }
        return userId.length;
    }

    @Override
    public List<WxUserCoupon> findByUserId(String userId) {
        return wxUserCouponMapper.findByUserId(userId);
    }
}