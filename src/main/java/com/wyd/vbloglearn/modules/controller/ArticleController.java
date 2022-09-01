package com.wyd.vbloglearn.modules.controller;


import com.wyd.vbloglearn.modules.mapper.ArticleMapper;
import com.wyd.vbloglearn.modules.model.Article;
import com.wyd.vbloglearn.modules.service.impl.ArticleServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author wyd
 * @since 2022-08-31 22:09:07
 */
@RestController
@RequestMapping("/article")
@Api(tags = "ArticleController" , description = "博客文章管理")
public class ArticleController {

    @Autowired
    private ArticleServiceImpl articleService;

    @GetMapping("getAllArticles")
    @ApiOperation("获取所有博客列表")
    public void getAllArticles() {
        List<Article> articles = articleService.list();
        System.out.println(articles);
    }
}

