package com.ops.controller;

import com.ops.dao.ShopApplyMapper;
import com.ops.entity.ShopApply;
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
@Api("农场申请")
@RequestMapping("shopapply")
public class ShopApplyController {

    @Autowired
    private ShopApplyMapper shopApplyMapper;


    @PostMapping("add")
    @ApiOperation(value = "添加申请", httpMethod = "POST")
    public void add(HttpServletRequest request, HttpServletResponse response, @ModelAttribute @Validated ShopApply apply, BindingResult result) {
        Util.checkParams(result);
        shopApplyMapper.insert(apply);
        new ResultUtil().success(request, response, "添加成功");
    }


    @PostMapping("find")
    @ApiOperation(value = "查询申请", httpMethod = "POST")
    public void find(HttpServletRequest request, HttpServletResponse response) {
        List<ShopApply> list = shopApplyMapper.find();
        new ResultUtil().push("list", list).out(request, response);
        ;
    }
}
