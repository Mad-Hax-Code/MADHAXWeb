package com.madhax.website.controller;

import com.madhax.website.domain.Article;
import com.madhax.website.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/blog")
public class BlogController {

    @Autowired
    private ArticleService articleService;

    @GetMapping
    public String blog(Model model) {
        model.addAttribute("articles", articleService.getAllArticles());
        return "blog";
    }

    @GetMapping("/add")
    public String addArticle(Model model) {
        model.addAttribute("article", new Article());
        return "blog/addArticle";
    }

    @GetMapping("/{id}")
    public String viewArticle(@PathVariable long id, Model model) {
        Article article = articleService.getArticleById(id).get();
        model.addAttribute("article", article);
        return "blog/article";
    }
}
