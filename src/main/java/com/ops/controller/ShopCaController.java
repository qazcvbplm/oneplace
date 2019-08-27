package com.ops.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import ops.model.X.area.entity.Functions;
import ops.model.X.area.service.FunctionsService;
import ops.model.X.base.page.PageAble;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class ShopCaController {

    @Autowired
    private FunctionsService functionsService;


    @PostMapping("/ca/add")
    public boolean add(@RequestBody Functions functions) {
        functions.setType("2");
        functions.setAreaId(0L);
        functions.setParentId(0L);
        functionsService.save(functions);
        return true;
    }

    @GetMapping("/ca/find")
    public IPage<Functions> add(@ModelAttribute PageAble pageAble) {
        return functionsService.page(pageAble.getPage(), new QueryWrapper<Functions>().lambda().eq(Functions::getType, "2"));
    }
}
