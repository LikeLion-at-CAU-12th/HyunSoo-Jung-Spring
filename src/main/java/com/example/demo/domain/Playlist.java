package com.example.demo.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;

import java.util.List;

import static jakarta.persistence.GenerationType.IDENTITY;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Playlist {
    // 제목, 설명, 소유자
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "playlist_id")
    private Long id;

    // @Column(nullable = false)
    @NotNull
    private String title;
    private String description;

    @ManyToOne
    @JoinColumn(name="member_id")
    private Member member; // ManyToOne

//    @OneToMany(mappedBy = "playlist")
//    private List<PlaylistSong> playlistSongs;
}