package com.alcoholchat.security.service;

import com.alcoholchat.domain.entity.Member;
import com.alcoholchat.repository.MemberRepository;
import com.alcoholchat.security.dto.CustomUserDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final MemberRepository memberRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        Optional<Member> member = memberRepository.findByEmail(email);

        if (member.isPresent()) {

            return new CustomUserDetails(member.get());
        }

        throw new UsernameNotFoundException("존재하지 않는 회원입니다");
    }
}
