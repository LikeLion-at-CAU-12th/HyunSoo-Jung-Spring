package com.example.demo.repository;

import com.example.demo.domain.Song;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public class SongRepository {

    @PersistenceContext
    EntityManager em;

    public Long save(Song song) {
        em.persist(song);
        return song.getId();
    }

    public Song findOne(Long id) {
        return em.find(Song.class, id);
    }

    public List<Song> findByTitle(String title) {
        return em.createQuery("select s from Song s where s.title = :title", Song.class)
                .setParameter("title", title)
                .getResultList();
    }
}
