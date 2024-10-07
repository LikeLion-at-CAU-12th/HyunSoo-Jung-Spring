package com.example.demo.repository;

import com.example.demo.domain.ArticleLog;
import com.example.demo.domain.Article;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ArticleLogJpaRepository extends JpaRepository<ArticleLog, Long>{
    Optional<ArticleLog> findByArticle(Article article);
}
