package com.ops.controller;


import com.ops.dao.AppMapper;
import com.ops.dao.PlaceOpertionMapper;
import com.ops.dao.WxUserMapper;
import com.ops.entity.App;
import com.ops.entity.PlaceOpertion;
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
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Api("操作")
@RequestMapping("placeOpertion")
@Controller
public class PlaceOpertionController {

    @Autowired
    private PlaceOpertionMapper placeOpertionDao;

    @Autowired
    private AppMapper appMapper;
    @Autowired
    private WxUserMapper wxUserMapper;

    @PostMapping("add")
    @ApiOperation(value = "浇水，施肥，除虫", httpMethod = "POST")
    public void add(HttpServletRequest request, HttpServletResponse response, @ModelAttribute @Validated PlaceOpertion po, BindingResult result) {
        Util.checkParams(result);
        App app = appMapper.selectByPrimaryKey(1);
        BigDecimal amount = null;
        switch (po.getType()) {
            case "浇水":
                amount = app.getJs();
                break;
            case "除虫":
                amount = app.getCc();
                break;
            case "施肥":
                amount = app.getSf();
                break;
        }
        Map<String, Object> mo = new HashMap<>();
        mo.put("userId", po.getUserid());
        mo.put("amount", amount);
        if (wxUserMapper.payMoney(mo) == 1) {
            po.setResult("待审核");
            placeOpertionDao.insert(po);
            new ResultUtil().success(request, response, "添加成功");
        } else {
            new ResultUtil().error(request, response, "余额不足");
        }
    }


    @PostMapping("findbyplace")
    @ApiOperation(value = "按地查询操作", httpMethod = "POST")
    public void findbyplace(HttpServletRequest request, HttpServletResponse response, String placeId) {
        List<PlaceOpertion> list = placeOpertionDao.findByPlaceId(placeId);
        new ResultUtil().push("list", list).out(request, response);
        ;
    }

    @PostMapping("findbyplace2")
    @ApiOperation(value = "按地查询操作", httpMethod = "POST")
    public void findbyplace2(HttpServletRequest request, HttpServletResponse response, String placeId) {
        List<PlaceOpertion> list = placeOpertionDao.findByPlaceId2(placeId);
        new ResultUtil().push("list", list).out(request, response);
        ;
    }


    @PostMapping("complete")
    @ApiOperation(value = "批量完成操作", httpMethod = "POST")
    public void complete(HttpServletRequest request, HttpServletResponse response, int[] id) {
        placeOpertionDao.complete(id);
        new ResultUtil().success(request, response, "ok");
        ;
    }

}
