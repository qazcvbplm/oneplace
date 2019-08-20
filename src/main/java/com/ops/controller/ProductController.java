package com.ops.controller;

import com.ops.entity.Product;
import com.ops.servicedao.ProductService;
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
@RequestMapping("product")
@Api("商品")
public class ProductController {

    @Autowired
    private ProductService productService;


    @PostMapping("add")
    @ApiOperation(value = "添加商品", httpMethod = "POST")
    public void update(HttpServletRequest request, HttpServletResponse response, @ModelAttribute @Validated Product product, BindingResult result) {
        Util.checkParams(result);
        String Id = productService.add(product);
        new ResultUtil().success(request, response, "添加成功");
    }


    @PostMapping("update")
    @ApiOperation(value = "更新商品", httpMethod = "POST")
    public void update(HttpServletRequest request, HttpServletResponse response, Product product) {
        int count = productService.update(product);
        if (count == 1)
            new ResultUtil().success(request, response, "更新成功");
        else
            new ResultUtil().error(request, response, "更新失败");
    }


    @PostMapping("findbycategory")
    @ApiOperation(value = "按照分类id查询商品", httpMethod = "POST")
    public void update(HttpServletRequest request, HttpServletResponse response, String categoryId) {
        List<Product> list = productService.findByCategoryId(categoryId);
        new ResultUtil().push("list", list).out(request, response);
    }


    @PostMapping("findbyid")
    @ApiOperation(value = "按照id查询商品", httpMethod = "POST")
    public void update(HttpServletRequest request, HttpServletResponse response, int id) {
        Product list = productService.findById(id);
        new ResultUtil().push("list", list).out(request, response);
    }

}
