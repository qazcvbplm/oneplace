package com.ops.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.ops.entity.ProductB;
import com.ops.servicedao.ProductBService;
import io.swagger.annotations.Api;
import ops.model.X.base.page.PageAble;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/product/b")
@Api("商品b_")
public class ProductBController {

    @Autowired
    private ProductBService productBService;

    @PostMapping("/add")
    public Boolean add(@RequestBody ProductB productB) {
        productBService.save(productB);
        return true;
    }

    @PostMapping("/delete")
    public Boolean add(Long id) {
        productBService.removeById(id);
        return true;
    }

    @GetMapping("/list")
    public IPage<ProductB> add(@RequestParam(required = false) Long caId,
                               @ModelAttribute PageAble pageAble) {
        QueryWrapper<ProductB> query = new QueryWrapper<>();
        if (caId != null) {
            query.lambda().eq(ProductB::getCaId, caId);
        }
        return productBService.page(pageAble.getPage(), query);
    }
}
