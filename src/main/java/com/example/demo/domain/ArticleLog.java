package com.example.demo.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import static jakarta.persistence.CascadeType.ALL;
import static jakarta.persistence.FetchType.LAZY;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ArticleLog extends BaseTimeEntity {
    @Id @GeneratedValue
    @Column(name = "article_log_id")
    private Long id;
    private String title;
    private String content;

    @OneToOne(cascade = ALL, fetch = LAZY)
    @JoinColumn(name = "article_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Article article;

    @Builder
    public ArticleLog(String title, String content, Article article) {
        this.title = title;
        this.content = content;
        this.article = article;
    }
}
