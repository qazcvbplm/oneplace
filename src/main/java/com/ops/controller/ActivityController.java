package com.ops.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.ops.dto.ActivityAdd;
import com.ops.dto.ProductBsResp;
import com.ops.entity.ActivityB;
import com.ops.entity.ProductB;
import com.ops.servicedao.ActivityBService;
import com.ops.servicedao.ProductBService;
import ops.model.X.area.entity.Functions;
import ops.model.X.area.service.FunctionsService;
import ops.model.X.base.page.PageAble;
import ops.model.X.dto.Deletes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class ActivityController {

    @Autowired
    private FunctionsService functionsService;

    @Autowired
    private ActivityBService activityBService;

    @Autowired
    private ProductBService productBService;


    @PostMapping("/activity/add")
    public boolean add(@RequestBody @Validated ActivityAdd activityAdd) {
        Functions functions = new Functions();
        functions.setTitle(activityAdd.getTitle());
        functions.setSubTitle(activityAdd.getSubTitle());
        functions.setImage(activityAdd.getImage());
        functions.setRichText(activityAdd.getRichText());
        functions.setType(activityAdd.getType());
        functions.setAreaId(0L);
        functions.setParentId(0L);
        functions.setRemark(activityAdd.getRemark());
        functionsService.save(functions);
        return true;
    }

    @PostMapping("/activity/bm")
    public boolean add(@RequestBody ActivityB activityB) {
        if (activityBService.count(new QueryWrapper<ActivityB>()
                .lambda().eq(ActivityB::getActivityId, activityB.getActivityId())
                .eq(ActivityB::getUserId, activityB.getUserId())) > 0) {
            throw new RuntimeException("已经报名过");
        }
        Functions activity = functionsService.getById(activityB.getActivityId());
        if (activity == null) {
            throw new RuntimeException("活动不存在");
        }
        if (activity.getRemark() != null) {

        } else {
            activityBService.save(activityB);
        }
        return true;
    }


    @PostMapping("/activity/update")
    public boolean update(@RequestBody Functions functions) {
        functionsService.updateById(functions);
        return true;
    }

    @PostMapping("/activity/delete")
    public boolean delete(@RequestBody Deletes deletes) {
        return functionsService.removeByIds(deletes.getIds());
    }

    @GetMapping("/activity/find")
    public IPage find(@ModelAttribute PageAble pageAble) {
        return functionsService.page(pageAble.getIPage(),
                new QueryWrapper<Functions>().lambda()
                        .eq(Functions::getType, "1"));
    }

    @GetMapping("/activity2/find")
    public List<ProductBsResp> find2(@ModelAttribute PageAble pageAble) {
        IPage<Functions> rs = functionsService.page(pageAble.getIPage(),
                new QueryWrapper<Functions>().lambda()
                        .eq(Functions::getType, "2"));
        List<ProductBsResp> result = new ArrayList<>();
        for (Functions temp : rs.getRecords()) {
            result.add(new ProductBsResp(temp, productBService.list(new QueryWrapper<ProductB>().lambda().eq(ProductB::getCaId, temp.getId()))));
        }
        return result;
    }
}
