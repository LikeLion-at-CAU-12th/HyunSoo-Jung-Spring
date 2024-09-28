package com.example.demo.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;

import static jakarta.persistence.GenerationType.IDENTITY;

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

    @NotNull
    @ManyToOne
    @JoinColumn(name="artist_id")
    private Artist artist; // ManyToOne

    @NotNull
    @ManyToOne
    @JoinColumn(name="album_id")
    private Album album; // ManyToOne

//    @OneToMany(mappedBy = "song", cascade = CascadeType.ALL, orphanRemoval = true)
//    private List<SongGenre> songGenres;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate releaseDate;

//    @OneToMany(mappedBy = "song", cascade = CascadeType.ALL, orphanRemoval = true)
//    private List<Liked> likes; // OneToMany

    @Builder
    public Song(String title, Artist artist, Album album, LocalDate releaseDate) {
        this.title = title;
        this.artist = artist;
        this.album = album;
        this.releaseDate = releaseDate;
    }
}