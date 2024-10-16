package com.example.demo.controller;

import com.example.demo.dto.request.ArticleCreateRequestDto;
import com.example.demo.dto.request.ArticleUpdateRequestDto;
import com.example.demo.dto.response.ArticleResponseDto;
import com.example.demo.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/articles")
public class ArticleController {
    @Autowired
    private ArticleService articleService;

    @PostMapping("")
    public ResponseEntity<Long> createArticle(@RequestBody ArticleCreateRequestDto requestDto) {
        Long articleId = articleService.createArticle(requestDto);
        return new ResponseEntity<>(articleId, HttpStatus.CREATED);
    }

    @GetMapping("/member/{memberId}")
    public ResponseEntity<List<ArticleResponseDto>> getArticlesByMemberId(@PathVariable("memberId") Long memberId) {
        List<ArticleResponseDto> articles = articleService.findArticlesByMemberId(memberId);
        if (articles.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(articles);
    }

    @PutMapping("/{articleId}")
    public ResponseEntity<Long> updateArticle(@PathVariable("articleId") Long articleId, @RequestBody ArticleUpdateRequestDto requestDto) {
        requestDto.setId(articleId);

        Long updatedArticleId = articleService.updateArticle(requestDto);
        return new ResponseEntity<>(updatedArticleId, HttpStatus.OK);
    }

    @DeleteMapping("/{articleId}")
    public ResponseEntity<Void> deleteArticle(@PathVariable("articleId") Long articleId) {
        articleService.deleteArticle(articleId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

//    @DeleteMapping("/{articleId}")
//    public ResponseEntity<Void> deleteArticle(@PathVariable("articleId") Long articleId) {
//        articleService.deleteArticle(articleId);
//        }
}
