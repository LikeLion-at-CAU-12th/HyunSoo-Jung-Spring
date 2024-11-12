package com.example.demo.repository;

import com.example.demo.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

@Repository
public interface MemberJpaRepository extends JpaRepository<Member, Long> {

    // List<Member> findByUsername(String username);
    Member findByUsername(String username);
    Page<Member> findByAgeGreaterThanEqual(int age, Pageable pageable);
    Page<Member> findByUsernameStartingWith(String username, Pageable pageable);
    boolean existsByUsername(String username);
    Optional<Member> findByEmail(String email); // 이메일 기반 검색 메서드 추가
//    boolean existsById(String email);
}