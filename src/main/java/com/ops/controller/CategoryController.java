package com.ops.controller;

import com.ops.entity.Category;
import com.ops.servicedao.CategoryService;
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
@Api("商品分类")
@RequestMapping("category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @PostMapping("add")
    @ApiOperation(value = "添加分类", httpMethod = "POST")
    public void add(HttpServletRequest request, HttpServletResponse response, @ModelAttribute @Validated Category category, BindingResult result) {
        Util.checkParams(result);
        String id = categoryService.add(category);
        new ResultUtil().success(request, response, "添加成功");
    }

    @PostMapping("update")
    @ApiOperation(value = "更新分类", httpMethod = "POST")
    public void update(HttpServletRequest request, HttpServletResponse response, @ModelAttribute Category category) {
        int count = categoryService.update(category);
        if (count == 1)
            new ResultUtil().success(request, response, "更新成功");
        else
            new ResultUtil().error(request, response, "更新失败");
    }


    @PostMapping("findbyshop")
    @ApiOperation(value = "按照店铺查询分类", httpMethod = "POST")
    public void findbyshop(HttpServletRequest request, HttpServletResponse response, String shopId) {
        List<Category> list = categoryService.findByShopId(shopId);
        new ResultUtil().push("list", list).out(request, response);
    }
}
