package com.ops.controller;

import com.ops.dao.AppMapper;
import com.ops.entity.App;
import com.ops.entity.Article;
import com.ops.util.ResultUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Api("全局")
@RequestMapping("app")
@Controller
public class AppController {

    @Autowired
    private AppMapper appMapper;

    @PostMapping("find")
    @ApiOperation(value = "查询", httpMethod = "POST")
    public void add(HttpServletRequest request, HttpServletResponse response, Article article) {
        App list = appMapper.selectByPrimaryKey(1);
        new ResultUtil().push("list", list).out(request, response);
        ;
    }

    @PostMapping("update")
    @ApiOperation(value = "", httpMethod = "POST")
    public void add(HttpServletRequest request, HttpServletResponse response, App app) {
        int i = appMapper.updateByPrimaryKeySelective(app);
        new ResultUtil().success(request, response, "更新" + i + "条记录");
        ;
    }
}
