package com.alcoholchat.service;

import com.alcoholchat.domain.dto.MemberDTO;
import com.alcoholchat.domain.entity.Member;
import com.alcoholchat.domain.entity.Member;

import java.util.List;
import java.util.UUID;

public interface MemberService {
    // create
    Member saveMember(MemberDTO.Request request);
    // read
    Member findMember(UUID memberId);
    List<Member> findMemberList();
    // update
    Member updateMember(MemberDTO.Request request);
    // delete
    void deleteMember(UUID memberId);
}
