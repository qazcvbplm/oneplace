package com.ops.controller;

import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class Nologin {


    //xhrFields:{withCredentials:true},
    @RequestMapping("nologin")
    @ApiOperation(hidden = true, value = "")
    public void nologin(HttpServletResponse response, HttpServletRequest request) {
        new com.ops.util.ResultUtil().error(request, response, "无权限调用");
    }
}
