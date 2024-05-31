package com.alcoholchat.service.implement;

import com.alcoholchat.domain.dto.MemberDTO;
import com.alcoholchat.domain.entity.Member;
import com.alcoholchat.domain.enums.Role;
import com.alcoholchat.repository.MemberRepository;
import com.alcoholchat.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {
    private final MemberRepository memberRepository;
    private final BCryptPasswordEncoder encoder;
  
    @Override
    public Boolean saveMember(MemberDTO.SignUpDTO dto) {

        Member newMember = memberRepository.save(
                Member.builder()
                        .email(dto.getEmail())
                        .birth(dto.getBirth())
                        .password(encoder.encode(dto.getPassword()))
                        .nickname(dto.getNickname())
                        .phoneNum(dto.getPhoneNum())
                        .build()
        );

        if (newMember == null) {
            return false;
        }
        return true;
    }

    @Override
    public Member findMemberById(UUID memberId) {
        return memberRepository.findById(memberId).orElse(null);
    }

    @Override
    public Member findMemberByEmail(String memberEmail) {
        return memberRepository.findByEmail(memberEmail).orElse(null);
    }

    @Override
    public List<Member> findMemberList() {
        return List.of();
    }

    @Override
    public Member updateMember(MemberDTO.Request request) {
        return null;
    }

    @Override
    public void deleteMember(UUID memberId) {

    }
}
