package com.example.demo.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

import static jakarta.persistence.GenerationType.IDENTITY;

import org.jetbrains.annotations.NotNull;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDate;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Song {
    // 제목, 가수, 앨범, 발매일, 장르, 좋아요
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "song_id")
    private Long id;

    //@Column(nullable = false)
    @NotNull
    private String title;

    @ManyToOne
    @JoinColumn(name="artist_id")
    private Artist artist; // ManyToOne

    @ManyToOne
    @JoinColumn(name="album_id")
    private Album album; // ManyToOne

//    @OneToMany(mappedBy = "song", cascade = CascadeType.ALL, orphanRemoval = true)
//    private List<SongGenre> songGenres;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate releaseDate;

//    @OneToMany(mappedBy = "song", cascade = CascadeType.ALL, orphanRemoval = true)
//    private List<Like> likes; // OneToMany
}