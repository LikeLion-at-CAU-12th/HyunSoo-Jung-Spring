package com.example.demo.dto.response;

import com.example.demo.domain.Article;

import java.util.ArrayList;
import java.util.List;

public class ArticleUpdateResponseDto {
    private Long id;
    private String title;
    private String content;
    private List<Long> categoryIds;

//    public ArticleUpdateResponseDto(Article article) {
//        this.id = article.getId();
//        this.title = article.getTitle();
//        this.content = article.getContent();
//        this.categoryIds = new ArrayList<>();
//    }
}
