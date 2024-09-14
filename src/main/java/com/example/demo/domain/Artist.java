package com.example.demo.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;

import static jakarta.persistence.GenerationType.IDENTITY;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Artist {
    // 이름
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "artist_id")
    private Long id;

    // @Column(nullable = false)
    @NotNull
    private String name;
}