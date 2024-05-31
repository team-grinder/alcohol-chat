package com.alcoholchat.service;

import com.alcoholchat.domain.dto.MemberDTO;
import com.alcoholchat.domain.entity.Member;

import java.util.List;
import java.util.UUID;

public interface MemberService {
    // create
    Boolean saveMember(MemberDTO.SignUpDTO dto);
    // read
    Member findMemberById(UUID memberId);
    Member findMemberByEmail(String memberEmail);
    List<Member> findMemberList();
    // update
    Member updateMember(MemberDTO.Request request);
    // delete
    void deleteMember(UUID memberId);
}
