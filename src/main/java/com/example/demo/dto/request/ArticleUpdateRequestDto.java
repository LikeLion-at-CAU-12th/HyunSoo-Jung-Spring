package com.example.demo.dto.request;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ArticleUpdateRequestDto {
//    private Long id;
    private String title;
    private String content;
    private List<Long> categoryIds;
}
