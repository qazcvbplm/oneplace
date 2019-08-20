package com.ops.controller;

import com.ops.entity.Shop;
import com.ops.servicedao.ShopService;
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

@Controller
@RequestMapping("shop")
@Api("店铺")
public class ShopController {

    @Autowired
    private ShopService shopService;

    @PostMapping("add")
    @ApiOperation(value = "添加店铺", httpMethod = "POST")
    public void update(HttpServletRequest request, HttpServletResponse response, @ModelAttribute @Validated Shop shop,
                       BindingResult result) {
        Util.checkParams(result);
        String id = shopService.add(shop);
        new ResultUtil().success(request, response, "添加成功");
    }

    @PostMapping("find")
    @ApiOperation(value = "查询店铺", httpMethod = "POST")
    public void find(HttpServletRequest request, HttpServletResponse response, @ModelAttribute Shop shop) {
        List<Shop> shops = shopService.find(shop);
        new ResultUtil().push("list", shops).out(request, response);
    }

    @PostMapping("update")
    @ApiOperation(value = "更新店铺", httpMethod = "POST")
    public void update(HttpServletRequest request, HttpServletResponse response, @ModelAttribute Shop shop) {
        if (shop.getId() != null) {
            shop.setIds(shop.getId().split(","));
        }
        int i = shopService.update(shop);
        new ResultUtil().push("result", i).out(request, response);
    }

}
