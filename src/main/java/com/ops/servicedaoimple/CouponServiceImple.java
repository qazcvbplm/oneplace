package com.ops.servicedaoimple;

import com.ops.dao.CouponMapper;
import com.ops.entity.Coupon;
import com.ops.servicedao.CouponService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CouponServiceImple implements CouponService {

    @Autowired
    private CouponMapper couponMapper;

    @Override
    public void add(Coupon coupon) {
        couponMapper.insert(coupon);

    }

    @Override
    public List<Coupon> find(Coupon coupon) {
        // TODO Auto-generated method stub
        return couponMapper.find(coupon);
    }
}
