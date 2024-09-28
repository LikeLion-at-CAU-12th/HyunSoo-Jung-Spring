package com.example.demo.service;

import com.example.demo.domain.Member;
import com.example.demo.repository.MemberJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberJpaRepository memberJpaRepository;

    public Page<Member> getMembersByPage(int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("username").ascending());
        return memberJpaRepository.findAll(pageable);
    }

    public void printMembersByPage(int page, int size) {
        Page<Member> memberPage = getMembersByPage(page, size);
        List<Member> members = memberPage.getContent();

        for (Member member : members) {
            System.out.println("ID: " + member.getId() + ", Username: " + member.getUsername());
        }
    }

    public Page<Member> getMembersByAgeGreaterThanEqual(int age, int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("username").ascending());
        return memberJpaRepository.findByAgeGreaterThanEqual(age, pageable);
    }
    public void printMembersByAgeGreaterThanEqual(int age, int page, int size) {
        Page<Member> memberPage = getMembersByAgeGreaterThanEqual(age, page, size);
        List<Member> members = memberPage.getContent();

        for (Member member : members) {
            System.out.println("ID: " + member.getId() + ", Username: " + member.getUsername() + ", Age: " + member.getAge());
        }
    }

    public Page<Member> getMembersByUsernameLike(String username, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return memberJpaRepository.findByUsernameStartingWith(username, pageable);
    }

    public void printMembersByUsernameLike(String username, int page, int size) {
        Page<Member> memberPage = getMembersByUsernameLike(username, page, size);
        List<Member> members = memberPage.getContent();

        for (Member member : members) {
            System.out.println("ID: " + member.getId() + ", Username: " + member.getUsername());
        }
    }
}