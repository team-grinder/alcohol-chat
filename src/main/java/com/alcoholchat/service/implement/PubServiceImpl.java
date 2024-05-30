package com.alcoholchat.service.implement;

import com.alcoholchat.domain.dto.PubDTO;
import com.alcoholchat.domain.entity.Member;
import com.alcoholchat.domain.entity.Pub;
import com.alcoholchat.repository.PubRepository;
import com.alcoholchat.service.MemberService;
import com.alcoholchat.service.PubService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PubServiceImpl implements PubService {
    private final PubRepository pubRepository;
    private final MemberService memberService;

    @Override
    public Pub savePub(PubDTO.Request request) {
        Member member = memberService.findMemberByEmail(request.getMember_email());
        Pub newPub = Pub.builder()
                     .member(member)
                     .name(request.getName())
                     .address(request.getAddress())
                     .description(request.getDescription())
                     .build();
        return pubRepository.save(newPub);
    }

    @Override
    public Pub findPub(UUID pubId) {
        return pubRepository.findById(pubId).orElse(null);
    }

    @Override
    public List<Pub> findPubList() {
        return List.of();
    }

    @Override
    public Pub updatePub(PubDTO.Request request) {
        return null;
    }

    @Override
    public void deletePub(UUID pubId) {

    }
}
