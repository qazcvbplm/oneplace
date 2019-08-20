package com.ops.servicedao;

import com.ops.entity.Article;

import java.util.List;

public interface ArticleService {

    void add(Article article);

    List<Article> find(Article article);

    void hf(String userId, String content, Integer id);

    List<Article> finduf(Integer id);

}
