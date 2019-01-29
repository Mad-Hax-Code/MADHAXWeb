package com.madhax.website.service;

import com.madhax.website.domain.Article;
import org.springframework.data.repository.CrudRepository;

public interface ArticleRepository extends CrudRepository<Article, Long> {
}
