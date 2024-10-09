package com.example.demo.repository;

import com.example.demo.domain.Article;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ArticleJpaRepository extends JpaRepository<Article, Long> {
    List<Article> findByMemberId(Long memberId);
}
