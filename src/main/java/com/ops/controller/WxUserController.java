package com.ops.controller;

import com.ops.entity.WxUser;
import com.ops.entity.WxUserBackPack;
import com.ops.entity.WxUserFollow;
import com.ops.servicedao.PlaceService;
import com.ops.servicedao.WxUserFollowService;
import com.ops.servicedao.WxUserService;
import com.ops.sunwou.wx.WXUtil;
import com.ops.util.ResultUtil;
import com.ops.util.Util;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("wxuser")
@Api("微信用户")
public class WxUserController {

    @Autowired
    private WxUserService wxUserService;
    @Autowired
    private WxUserFollowService wxUserFollowService;
    @Autowired
    private PlaceService placeService;
    public static Map<String, String> app;

    static {
        app = new HashMap<>();
        app.put("appid", "wxa20c7ab64fb6ba74");
        app.put("secert", "75fc46e00e26bc6c2251155d98add2e8");
        app.put("mch_id", "1503604781");
        app.put("paykey", "csB18857818257332522199510208595");
    }


    @GetMapping("login")
    @ApiOperation(value = "注册用户", httpMethod = "POST")
    public void adduser(HttpServletRequest request, HttpServletResponse response, String code) {
        String openid = WXUtil.wxlogin(app.get("appid"), app.get("secert"), code);
        WxUser wxUser = wxUserService.login(openid);
        new ResultUtil().push("user", wxUser).out(request, response);
    }

    @PostMapping("update")
    @ApiOperation(value = "更新用户信息", httpMethod = "POST")
    public void update(HttpServletRequest request, HttpServletResponse response, WxUser wxUser) {
        wxUser = wxUserService.update(wxUser);
        new ResultUtil().push("user", wxUser).out(request, response);
    }

    @PostMapping("find")
    @ApiOperation(value = "查询用户信息", httpMethod = "POST")
    public void find(HttpServletRequest request, HttpServletResponse response, @ModelAttribute WxUser wxUser) {
        List<WxUser> list = wxUserService.find(wxUser);
        for (WxUser temp : list) {
            temp.setPlaces(placeService.findByUserId(temp.getOpenid()));
        }
        new ResultUtil().push("list", list).out(request, response);
    }


    @PostMapping("concat")
    @ApiOperation(value = "关注/取消关注", httpMethod = "POST")
    public void concat(HttpServletRequest request, HttpServletResponse response, @ModelAttribute @Validated WxUserFollow wuf, BindingResult result) {
        Util.checkParams(result);
        if (wxUserFollowService.add(wuf)) {
            new ResultUtil().success(request, response, "关注成功");
        } else {
            new ResultUtil().error(request, response, "已取消关注");
        }
    }


    @PostMapping("findzz")
    @ApiOperation(value = "查询我的种子", httpMethod = "POST")
    public void find(HttpServletRequest request, HttpServletResponse response, String userId) {
        List<WxUserBackPack> list = wxUserService.findzz(userId);
        new ResultUtil().push("list", list).out(request, response);
    }

}
