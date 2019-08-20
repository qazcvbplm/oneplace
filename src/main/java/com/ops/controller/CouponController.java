package com.ops.controller;

import com.ops.entity.Coupon;
import com.ops.servicedao.CouponService;
import com.ops.util.ResultUtil;
import com.ops.util.Util;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Api("优惠券模板")
@Controller
@RequestMapping("coupon")
public class CouponController {

    @Autowired
    private CouponService couponService;


    @PostMapping("add")
    @ApiOperation(value = "添加优惠券模板", httpMethod = "POST")
    public void add(HttpServletRequest request, HttpServletResponse response, @ModelAttribute @Validated Coupon coupon, BindingResult result) {
        Util.checkParams(result);
        couponService.add(coupon);
        new ResultUtil().success(request, response, "添加成功");
    }


    @PostMapping("find")
    @ApiOperation(value = "查询优惠券模板", httpMethod = "POST")
    public void add(HttpServletRequest request, HttpServletResponse response, Coupon coupon) {
        List<Coupon> list = couponService.find(coupon);
        new ResultUtil().push("list", list).out(request, response);
        ;
    }

}
