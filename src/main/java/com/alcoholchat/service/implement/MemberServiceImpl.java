package com.alcoholchat.service.implement;

import com.alcoholchat.domain.dto.MemberDTO;
import com.alcoholchat.domain.entity.Member;
import com.alcoholchat.repository.MemberRepository;
import com.alcoholchat.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {
    private final MemberRepository memberRepository;

    @Override
    public Member saveMember(MemberDTO.Request request) {
        return null;
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
