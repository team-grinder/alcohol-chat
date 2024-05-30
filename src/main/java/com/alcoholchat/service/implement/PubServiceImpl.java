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
    public Pub savePub(String memberEmail, PubDTO.Request request) {
        Member member = memberService.findMemberByEmail(memberEmail);
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
        return pubRepository.findByIsDeletedFalse();
    }

    @Override
    public Pub updatePub(UUID pubId, PubDTO.Request request) {
        Pub pub = findPub(pubId);
        pub.update(request);
        return pubRepository.save(pub);
    }

    @Override
    public Pub deletePub(UUID pubId) {
        Pub pub = findPub(pubId);
        pub.delete();
        return pubRepository.save(pub);
    }
}
