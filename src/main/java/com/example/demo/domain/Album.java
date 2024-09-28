package com.example.demo.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;

import java.util.List;

import static jakarta.persistence.GenerationType.IDENTITY;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Album {
    // 제목
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "album_id")
    private Long id;

    // @Column(nullable = false)
    @NotNull
    private String title;

    @Builder
    public Album(String title) {
        this.title = title;
    }

//    @NotNull
//    @ManyToOne
//    @JoinColumn(name="artist_id")
//    private Artist artist; // ManyToOne

//    @OneToMany(mappedBy = "album", cascade = CascadeType.ALL, orphanRemoval = true)
//    private List<Song> song; // OneToMany
}