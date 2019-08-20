package com.ops.controller;

import com.ops.servicedao.TestService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("test")
@Api("测试接口")
public class TestController {


    @Autowired
    private TestService testService;

    @PostMapping("test")
    @ApiOperation(value = "测试", httpMethod = "POST")
    public void test(HttpServletRequest request, HttpServletResponse response) {
        testService.test();
		/*UsernamePasswordToken token=new UsernamePasswordToken("123", "123");
		try {
			SecurityUtils.getSubject().login(token);
		} catch (UnknownAccountException e) {
			new com.com.ops.util.ResultUtil().error(request, response, "账号或密码错误");
		}
		new com.com.ops.util.ResultUtil().success(request, response, "ok");*/
    }

}
