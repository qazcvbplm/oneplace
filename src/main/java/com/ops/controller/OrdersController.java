package com.ops.controller;

import com.ops.dto.OrderAddRequest;
import com.ops.entity.Orders;
import com.ops.servicedao.OrdersService;
import com.ops.sunwou.exception.MyException;
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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Controller
@Api("订单")
@RequestMapping("orders")
public class OrdersController {

    @Autowired
    private OrdersService ordersService;

    @PostMapping("add")
    @ApiOperation(value = "下单", httpMethod = "POST")
    public void add(HttpServletRequest request, HttpServletResponse response, Integer[] productIds, Integer[] numbers,
                    @ModelAttribute @Validated Orders orders, BindingResult result) {
        Util.checkParams(result);
        if (productIds.length != numbers.length || numbers.length == 0) {
            throw new MyException("参数有误");
        }
        String orderId = ordersService.add(productIds, numbers, orders);
        new ResultUtil().success(request, response, orderId);
    }

    @PostMapping("add2")
    @ApiOperation(value = "商城下单", httpMethod = "POST")
    public void add2(HttpServletRequest request,
                     HttpServletResponse response,
                     @RequestBody OrderAddRequest orderAddRequest) {
        String orderId = ordersService.add2(orderAddRequest);
        new ResultUtil().success(request, response, orderId);
    }

    @PostMapping("charge")
    @ApiOperation(value = "充值", httpMethod = "POST")
    public void charge(HttpServletRequest request, HttpServletResponse response, String userId, BigDecimal amount) {
        String orderId = Util.GenerateOrderNumber(userId, UUID.randomUUID().toString().substring(0, 5));
        Orders order = new Orders().set(orderId, amount);
        order.setType("充值");
        order.setUserid(userId);
        order.setAddress("");
        order.setAddressName("");
        order.setAddressPhone("");
        order.setRemark("");
        Object msg = ordersService.charge(order, request.getRemoteAddr());
        new ResultUtil().push("msg", msg).out(request, response);
    }

    @PostMapping("pay")
    @ApiOperation(value = "付款", httpMethod = "POST")
    public void pay(HttpServletRequest request, HttpServletResponse response, String userId, String orderId) {
        if (userId != null && orderId != null) {
            String id = ordersService.pay(userId, orderId);
            new ResultUtil().push("id", id).out(request, response);
        }
    }

    @PostMapping("find")
    @ApiOperation(value = "查询订单", httpMethod = "POST")
    public void find(HttpServletRequest request, HttpServletResponse response, @ModelAttribute Orders orders) {
        List<Orders> list = ordersService.find(orders);
        new ResultUtil().push("list", list).out(request, response);
    }
}
