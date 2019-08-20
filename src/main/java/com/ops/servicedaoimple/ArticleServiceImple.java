package com.ops.servicedaoimple;

import com.ops.dao.ArticleMapper;
import com.ops.entity.Article;
import com.ops.servicedao.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ArticleServiceImple implements ArticleService {


    @Autowired
    private ArticleMapper articleMapper;

    @Override
    public void add(Article article) {
        articleMapper.insert(article);
    }

    @Override
    public List<Article> find(Article article) {
        // TODO Auto-generated method stub
        return articleMapper.find(article);
    }

    @Override
    public void hf(String userId, String content, Integer id) {
        Article article = new Article();
        article.setUserid(userId);
        article.setContent(content);
        article.setPid(id);
        articleMapper.hf(article);

    }

    @Override
    public List<Article> finduf(Integer id) {
        return articleMapper.findByPid(id);
    }
}
