package com.ops.dao;

import com.ops.entity.Article;

import java.util.List;

public interface ArticleMapper {
    int insert(Article record);

    Article selectByPrimaryKey(Integer id);

    List<Article> find(Article article);

    void hf(Article article);

    List<Article> findByPid(Integer id);
}