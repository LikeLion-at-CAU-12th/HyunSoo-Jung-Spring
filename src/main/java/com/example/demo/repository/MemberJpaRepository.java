package com.example.demo.repository;

import com.example.demo.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

@Repository
public interface MemberJpaRepository extends JpaRepository<Member, Long> {

    List<Member> findByUsername(String username);
    Page<Member> findByAgeGreaterThanEqual(int age, Pageable pageable);
    Page<Member> findByUsernameStartingWith(String username, Pageable pageable);
}