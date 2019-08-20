package com.ops.controller;

import com.ops.sunwou.wx.WXpayUtil;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
public class NotifyController {

    @RequestMapping("notify")
    @ApiOperation(value = "通知接口", hidden = true)
    public void notify(HttpServletResponse response, HttpServletRequest request) throws IOException {
        WXpayUtil.notify(request, response);
    }


}
