package com.madhax.website.service;

import com.madhax.website.domain.Article;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ArticleService {

    @Autowired
    ArticleRepository articleRepository;

    public List<Article> getAllArticles() {
        return (List<Article>) articleRepository.findAll();
    }

    public Optional<Article> getArticleById(long id) {
        return articleRepository.findById(id);
    }

    public void saveArticle(Article article) {
        articleRepository.save(article);
    }

    public void deleteArticle(Article article) {
        articleRepository.delete(article);
    }
}
