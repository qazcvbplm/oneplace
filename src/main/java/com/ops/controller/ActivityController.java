package com.ops.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.ops.entity.ActivityB;
import com.ops.servicedao.ActivityBService;
import ops.model.X.area.entity.Functions;
import ops.model.X.area.service.FunctionsService;
import ops.model.X.base.page.PageAble;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class ActivityController {

    @Autowired
    private FunctionsService functionsService;

    @Autowired
    private ActivityBService activityBService;


    @PostMapping("/activity/add")
    public boolean add(@RequestBody Functions functions) {
        functions.setType("1");
        functions.setAreaId(0L);
        functions.setParentId(0L);
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
        activityBService.save(activityB);
        return true;
    }


    @PostMapping("/activity/update")
    public boolean update(@RequestBody Functions functions) {
        functionsService.updateById(functions);
        return true;
    }

    @PostMapping("/activity/delete")
    public boolean delete(Long id) {
        functionsService.removeById(id);
        return true;
    }

    @GetMapping("/activity/find")
    public IPage<Functions> add(@ModelAttribute PageAble pageAble) {
        return functionsService.page(pageAble.getPage(), new QueryWrapper<Functions>().lambda().eq(Functions::getType, "1"));
    }
}
