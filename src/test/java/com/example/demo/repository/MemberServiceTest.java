package com.example.demo.repository;

import com.example.demo.domain.Member;
import com.example.demo.service.MemberService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Random;
import java.util.stream.IntStream;

@SpringBootTest
public class MemberServiceTest {

    @Autowired
    private MemberService memberService;

    @Autowired
    private MemberJpaRepository memberJpaRepository;

    private final Random random = new Random();

    // 테스트 실행 전, Member 100개 생성하는 코드입니다.
    @Test
    @BeforeEach
    public void setUp() {
        IntStream.range(0, 100).forEach(i -> {
            String username = "user" + random.nextInt(10000);
            String email = "user" + random.nextInt(10000) + "@example.com";
            int age = random.nextInt(60 - 18 + 1) + 18;
            Member member = Member.builder()
                    .username(username)
                    .email(email)
                    .age(age)
                    .build();
            memberJpaRepository.save(member);
        });
    }
    
    @Test
    public void testPrintMembersByPage() {
        // 이 부분은 숫자 바꿔가면서 출력해보세요 !
        memberService.printMembersByPage(0, 10);
    }

    @Test
    public void testPrintMembersByAgeGreaterThanEqual() {
        memberService.printMembersByAgeGreaterThanEqual(20, 0,10);
    }

    @Test
    public void testPrintMembersByUsernameLike() {
        memberService.printMembersByUsernameLike("user", 0, 10);
    }
}
