package com.example.demo.repository;

import com.example.demo.domain.Member;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class MemberRepository {

    @PersistenceContext
    EntityManager em;

    public Long save(Member member) { // Member 객체를 데이터베이스에 저장
        em.persist(member); // 엔티티를 영속성 컨텍스트에 추가
        return member.getId();
    }

    public Member findOne(Long id) { // ID 값으로 엔티티 조회
        return em.find(Member.class, id);
    }

    public List<Member> findAll() { // 모든 엔티티 조회
        return em.createQuery("select m from Member m", Member.class).getResultList(); // JPQL을 이용해서 받아오는 방법
    }

    public List<Member> findByUsername(String username) { // username 값으로 엔티티 조회
        return em.createQuery("select m from Member m where m.username = :username", Member.class)
                .setParameter("username", username)
                .getResultList();
    }

    public List<Member> findByEmail(String email) { // email 값으로 엔티티 조회
        return em.createQuery("select m from Member m where m.email = :email", Member.class)
                .setParameter("email", email)
                .getResultList();
    }
}