package com.ops.controller;

import com.ops.dao.ShMapper;
import com.ops.entity.Sh;
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

@Api("果子成熟")
@RequestMapping("sh")
@Controller
public class ShController {

    @Autowired
    private ShMapper shMapper;

    @PostMapping("add")
    @ApiOperation(value = "发帖", httpMethod = "POST")
    public void add(HttpServletRequest request, HttpServletResponse response, @ModelAttribute @Validated Sh sh, BindingResult result) {
        Util.checkParams(result);
        shMapper.insertSelective(sh);
        new ResultUtil().success(request, response, "添加成功");
    }

    @PostMapping("update")
    @ApiOperation(value = "更新", httpMethod = "POST")
    public void add(HttpServletRequest request, HttpServletResponse response, Sh sh) {
        int i = shMapper.updateByPrimaryKeySelective(sh);
        new ResultUtil().success(request, response, "更新" + i + "条记录");
        ;
    }

    @PostMapping("find")
    @ApiOperation(value = "查询", httpMethod = "POST")
    public void findbyid(HttpServletRequest request, HttpServletResponse response, Sh sh) {
        List<Sh> list = shMapper.find(sh);
        new ResultUtil().push("list", list).out(request, response);
    }
}
