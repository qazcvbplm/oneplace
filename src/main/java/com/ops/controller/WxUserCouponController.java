package com.ops.controller;

import com.ops.entity.WxUserCoupon;
import com.ops.servicedao.WxUserCouponService;
import com.ops.util.ResultUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
@Api("优惠券")
@RequestMapping("wxusercoupon")
public class WxUserCouponController {

    @Autowired
    private WxUserCouponService wxUserCouponService;


    @PostMapping("add")
    @ApiOperation(value = "发放优惠券", httpMethod = "POST")
    public void add(HttpServletRequest request, HttpServletResponse response, Integer couponId, String[] userId) {
        int count = wxUserCouponService.add(couponId, userId);
        new ResultUtil().push("count", count).out(request, response);
    }


    @PostMapping("find")
    @ApiOperation(value = "用户查询优惠券", httpMethod = "POST")
    public void add(HttpServletRequest request, HttpServletResponse response, String userId) {
        List<WxUserCoupon> list = wxUserCouponService.findByUserId(userId);
        new ResultUtil().push("count", list).out(request, response);
    }
}
