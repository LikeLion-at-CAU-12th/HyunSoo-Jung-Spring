//package com.example.demo.domain;
//
//import jakarta.persistence.*;
//import lombok.AccessLevel;
//import lombok.Getter;
//import lombok.NoArgsConstructor;
//
//import java.util.List;
//
//import static jakarta.persistence.GenerationType.IDENTITY;
//
//import org.springframework.format.annotation.DateTimeFormat;
//import java.time.LocalDate;
//
//@Entity
//@Getter
//@NoArgsConstructor(access = AccessLevel.PROTECTED)
//public class SongGenre {
//    // 곡, 장르
//    @Id
//    @GeneratedValue(strategy = IDENTITY)
//    @Column(name = "songgenre_id")
//    private Long id;
//
//    @ManyToOne
//    @JoinColumn(name="song_id")
//    private Song song; // ManyToOne
//
//    @Enumerated(EnumType.STRING)
//    @Column(name = "genre", nullable = false)
//    private Genre genre;
//}