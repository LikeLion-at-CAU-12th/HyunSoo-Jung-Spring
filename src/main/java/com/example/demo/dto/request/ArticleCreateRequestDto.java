package com.example.demo.dto.request;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class ArticleCreateRequestDto {
    private Long memberId;
    private String title;
    private String content;
    private List<Long> categoryIds;
}