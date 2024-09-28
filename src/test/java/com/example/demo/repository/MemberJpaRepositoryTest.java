package com.example.demo.repository;

import com.example.demo.domain.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class MemberJpaRepositoryTest {

    @Autowired
    MemberJpaRepository memberJpaRepository;

    @Test
    @Transactional
    public void testMember() {
        Member member = Member.builder()
                .username("memberA")
                .age(23)
                .email("polarpheno@gmail.com")
                .build();

        Member savedMember = memberJpaRepository.save(member);
        Member findMember = memberJpaRepository.findById(savedMember.getId()).orElse(null);

        Assertions.assertThat(findMember).isNotNull();
        Assertions.assertThat(findMember.getId()).isEqualTo(member.getId()); // 통과
        Assertions.assertThat(findMember).isEqualTo(member); // 통과
    }

    @Test
    @Transactional
    public void testMember2() {
        Member member = Member.builder()
                .username("memberA")
                .age(23)
                .email("polarpheno@gmail.com")
                .build();

        Member savedMember = memberJpaRepository.save(member);
        Member findMember = memberJpaRepository.findById(savedMember.getId()).orElse(null);

        Assertions.assertThat(findMember).isNotNull();
        Assertions.assertThat(findMember.getId()).isEqualTo(member.getId());
        Assertions.assertThat(findMember).isEqualTo(member);
    }

    @Test
    @Transactional
    public void testFindAll() {
        Member member1 = Member.builder()
                .username("member1")
                .age(23)
                .email("polarpheno@gmail.com")
                .build();

        Member member2 = Member.builder()
                .username("member2")
                .age(23)
                .email("polarpheno@gmail.com")
                .build();

        Member member3 = Member.builder()
                .username("member3")
                .age(23)
                .email("polarpheno@gmail.com")
                .build();


        memberJpaRepository.save(member1);
        memberJpaRepository.save(member2);
        memberJpaRepository.save(member3);


        List<Member> members = memberJpaRepository.findAll();

        for (Member member : members) {
            System.out.println(member.getId() + " " + member.getAge() + " " + member.getUsername());
        }
        Assertions.assertThat(members).hasSize(3);
    }

    @Test
    @Transactional
    public void testFindByUsername() {
        Member member = Member.builder()
                .username("memberA")
                .age(23)
                .email("polarpheno@gmail.com")
                .build();

        memberJpaRepository.save(member);

        List<Member> byUsername = memberJpaRepository.findByUsername(member.getUsername());

        for (Member member1 : byUsername) {
            System.out.println(member1.getId());
        }

        Assertions.assertThat(byUsername).isNotEmpty();
    }
}
