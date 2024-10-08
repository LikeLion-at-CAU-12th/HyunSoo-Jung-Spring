package com.example.demo.service;

import com.example.demo.domain.*;
import com.example.demo.dto.request.ArticleCreateRequestDto;
import com.example.demo.dto.request.ArticleUpdateRequestDto;
import com.example.demo.dto.response.ArticleResponseDto;
import com.example.demo.repository.*;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ArticleService {
    @Autowired
    private MemberJpaRepository memberJpaRepository;
    @Autowired
    private ArticleJpaRepository articleJpaRepository;
    @Autowired
    private CategoryArticleJpaRepository categoryArticleJpaRepository;
    @Autowired
    private ArticleLogJpaRepository articleLogJpaRepository;
    @Autowired
    private CategoryJpaRepository categoryJpaRepository;

    @Transactional
    public Long createArticle(ArticleCreateRequestDto requestDto) {
        Member member = memberJpaRepository.findById(requestDto.getMemberId())
                .orElseThrow(() -> new RuntimeException(("해당 아이디를 가진 회원이 존재하지 않습니다.")));
        Article article = Article.builder()
                .title(requestDto.getTitle())
                .content(requestDto.getContent())
                .member(member)
                .comments(new ArrayList<>())
                .build();
        articleJpaRepository.save(article);

        ArticleLog articleLog = ArticleLog.builder()
                .title(requestDto.getTitle())
                .content(requestDto.getContent())
                .article(article)
                .build();
        articleLogJpaRepository.save(articleLog);

        List<Long> categoryIds = requestDto.getCategoryIds();
        if (categoryIds != null && !categoryIds.isEmpty()) {
            for (Long categoryId : categoryIds) {
                Category category = categoryJpaRepository.findById(categoryId)
                        .orElseThrow(() -> new RuntimeException("해당 ID를 가진 카테고리가 없습니다."));

                CategoryArticle categoryArticle = CategoryArticle.builder()
                        .category(category)
                        .article(article)
                        .build();
                categoryArticleJpaRepository.save(categoryArticle);
            }
        }
        return article.getId();
    }

    public List<ArticleResponseDto> findArticlesByMemberId(Long memberId) {
        List<Article> articles = articleJpaRepository.findByMemberId(memberId);
        return articles.stream()
                .map(article -> new ArticleResponseDto(article.getId(), article.getTitle(), article.getContent()))
                .collect(Collectors.toList());
    }

    @Transactional
    public Long updateArticle(ArticleUpdateRequestDto requestDto) {
        Article article = articleJpaRepository.findById(requestDto.getId())
                .orElseThrow(() -> new RuntimeException("해당 ID를 가진 게시글을 찾을 수 없습니다."));

        article.setTitle(requestDto.getTitle());
        article.setContent(requestDto.getContent());

//        article.builder()
//                .title(requestDto.getTitle())
//                .content(requestDto.getContent())
//                .member(article.getMember())
//                .build();

        ArticleLog articleLog = ArticleLog.builder()
                .title(requestDto.getTitle())
                .content(requestDto.getContent())
                .article(article) // 게시글과 로그를 연결
                .build();
        articleLogJpaRepository.save(articleLog);

        List<Long> categoryIds = requestDto.getCategoryIds();
        if (categoryIds != null && !categoryIds.isEmpty()) {
            categoryArticleJpaRepository.deleteAllByArticle(article);
            for (Long categoryId : categoryIds) {
                Category category = categoryJpaRepository.findById(categoryId)
                        .orElseThrow(() -> new RuntimeException("해당 ID를 가진 카테고리가 없습니다."));

                CategoryArticle categoryArticle = CategoryArticle.builder()
                        .category(category)
                        .article(article)
                        .build();

                categoryArticleJpaRepository.save(categoryArticle);
            }
        }
        return article.getId();
    }

    @Transactional
    public void deleteArticle(Long articleId) {
        Article article = articleJpaRepository.findById(articleId)
                .orElseThrow(() -> new RuntimeException("해당 ID를 가진 게시글을 찾을 수 없습니다."));

//        articleLogJpaRepository.deleteAllByArticle(article);
//        categoryArticleJpaRepository.deleteAllByArticle(article);
        articleJpaRepository.delete(article);
    }

//    @Transactional
//    public void deleteArticle(Long articleId) {
//        Article article = articleJpaRepository.findById(articleId)
//                .orElseThrow(()-> new RuntimeException("해당 ID를 가진 게시글을 찾을 수 없습니다."));
////        categoryArticleJpaRepository.deleteAllByArticle(article);
//        articleJpaRepository.deleteById(article.getId());
//    }
}