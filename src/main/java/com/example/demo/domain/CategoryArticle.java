package com.example.demo.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import static jakarta.persistence.FetchType.LAZY;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CategoryArticle extends BaseTimeEntity {
    @Id @GeneratedValue
    @Column(name = "category_article_id")
    private Long id;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "category_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Category category;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "article_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Article article;

    @Builder
    public CategoryArticle(Category category, Article article) {
        this.category = category;
        this.article = article;
    }
}
