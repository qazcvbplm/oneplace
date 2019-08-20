package com.ops.controller;

import com.ops.entity.Article;
import com.ops.servicedao.ArticleService;
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

@Api("贴子")
@RequestMapping("article")
@Controller
public class ArticleController {

    @Autowired
    private ArticleService articleService;

    @PostMapping("add")
    @ApiOperation(value = "发帖", httpMethod = "POST")
    public void add(HttpServletRequest request, HttpServletResponse response, @ModelAttribute @Validated Article article, BindingResult result) {
        Util.checkParams(result);
        articleService.add(article);
        new ResultUtil().success(request, response, "添加成功");
    }

    @PostMapping("find")
    @ApiOperation(value = "查询帖", httpMethod = "POST")
    public void add(HttpServletRequest request, HttpServletResponse response, Article article) {
        List<Article> list = articleService.find(article);
        new ResultUtil().push("list", list).out(request, response);
        ;
    }

    @PostMapping("replay")
    @ApiOperation(value = "回复", httpMethod = "POST")
    public void add(HttpServletRequest request, HttpServletResponse response, Integer id, String userId, String content) {
        articleService.hf(userId, content, id);
        new ResultUtil().success(request, response, "回复成功");
    }


    @PostMapping("findreplay")
    @ApiOperation(value = "查询回复", httpMethod = "POST")
    public void add(HttpServletRequest request, HttpServletResponse response, Integer id) {
        List<Article> list = articleService.finduf(id);
        new ResultUtil().push("list", list).out(request, response);
        ;
    }
}
