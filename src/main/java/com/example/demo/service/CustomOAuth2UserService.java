package com.example.demo.service;

import com.example.demo.domain.Member;
import com.example.demo.repository.MemberJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
@RequiredArgsConstructor
public class CustomOAuth2UserService extends DefaultOAuth2UserService {

    private final MemberJpaRepository memberJpaRepository;
    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        // super.loadUser 호출 전에 access token을 가져옴
        String accessToken = userRequest.getAccessToken().getTokenValue();
        System.out.println("Access Token: " + accessToken); // 토큰 출력 (확인용)

        OAuth2User oAuth2User = super.loadUser(userRequest);

        String email = oAuth2User.getAttribute("email");
        String username = oAuth2User.getAttribute("name");

        Member member = memberJpaRepository.findByEmail(email)
                .map(existingMember -> updateMemberUsername(existingMember, username))
                .orElseGet(() -> Member.builder()
                        .email(email)
                        .username(username)
                        .password("")
                        .build());

        if (member.getId() == null) {
            memberJpaRepository.save(member);
        }
        return new DefaultOAuth2User(
                Collections.singleton(new SimpleGrantedAuthority("ROLE_MEMBER")),
                oAuth2User.getAttributes(),
                "sub");
    }
    private Member updateMemberUsername(Member existingMember, String newUsername) {
        if(!existingMember.getUsername().equals(newUsername)) {
            existingMember.setUsername(newUsername);
            memberJpaRepository.save(existingMember);
        }
        return existingMember;
    }
}
