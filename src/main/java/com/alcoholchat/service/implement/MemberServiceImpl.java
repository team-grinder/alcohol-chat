package com.alcoholchat.service.implement;

import com.alcoholchat.domain.dto.MemberDTO;
import com.alcoholchat.domain.entity.Member;
import com.alcoholchat.service.MemberService;

import java.util.List;
import java.util.UUID;

public class MemberServiceImpl implements MemberService {
    @Override
    public Member saveMember(MemberDTO.Request request) {
        return null;
    }

    @Override
    public Member findMember(UUID memberId) {
        return null;
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
