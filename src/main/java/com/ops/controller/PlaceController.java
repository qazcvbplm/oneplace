package com.ops.controller;

import com.ops.entity.Place;
import com.ops.entity.Use;
import com.ops.servicedao.PlaceService;
import com.ops.util.ResultUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
@RequestMapping("place")
@Api("地")
public class PlaceController {

    @Autowired
    private PlaceService placeService;

    @PostMapping("find")
    @ApiOperation(value = "查询", httpMethod = "POST")
    public void find(HttpServletRequest request, HttpServletResponse response, @ModelAttribute Place place) {
        List<Place> list = placeService.find(place);
        new ResultUtil().push("list", list).out(request, response);
    }

    @PostMapping("findbyid")
    @ApiOperation(value = "查询", httpMethod = "POST")
    public void findbyid(HttpServletRequest request, HttpServletResponse response, String id) {
        Place place = placeService.findById(id);
        new ResultUtil().push("list", place).out(request, response);
    }


    @PostMapping("findcon")
    @ApiOperation(value = "查询我关注的地", httpMethod = "POST")
    public void findcon(HttpServletRequest request, HttpServletResponse response, String userId) {
        List<Place> list = placeService.findByCon(userId);
        new ResultUtil().push("list", list).out(request, response);
    }


    @PostMapping("update_name")
    @ApiOperation(value = "改名字", httpMethod = "POST")
    public void update_name(HttpServletRequest request, HttpServletResponse response, String placeId, String name) {
        Place place = new Place(placeId, name);
        placeService.updateName(place);
        new ResultUtil().success(request, response, "改名成功");
    }


    @PostMapping("update")
    @ApiOperation(value = "更新", httpMethod = "POST")
    public void update(HttpServletRequest request, HttpServletResponse response, Place place) {
        placeService.update(place);
        new ResultUtil().success(request, response, "成功");
    }


    @PostMapping("bz")
    @ApiOperation(value = "使用种子", httpMethod = "POST")
    public void update_name(HttpServletRequest request, HttpServletResponse response, String[] ids, int[] count, @ModelAttribute Use use) {
        placeService.bz(ids, count, use);
        new ResultUtil().success(request, response, "等待审核");
    }

    @PostMapping("bzupdate")
    @ApiOperation(value = "更新种子使用状态", httpMethod = "POST")
    public void bzupdate(HttpServletRequest request, HttpServletResponse response, @ModelAttribute Use use) {
        placeService.bzupdate(use);
        new ResultUtil().success(request, response, "ok");
    }

    @PostMapping("bzfind")
    @ApiOperation(value = "查询使用状态", httpMethod = "POST")
    public void bzfind(HttpServletRequest request, HttpServletResponse response, @ModelAttribute Use use) {
        List<Use> list = placeService.findbz(use);
        new ResultUtil().push("list", list).out(request, response);
        ;
    }
}
