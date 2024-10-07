package com.example.demo.repository;

import com.example.demo.domain.Article;
import com.example.demo.domain.CategoryArticle;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategoryArticleJpaRepository extends JpaRepository<CategoryArticle, Long> {
    List<CategoryArticle> findByArticle(Article article);
    void deleteAllByArticle(Article article);
}
