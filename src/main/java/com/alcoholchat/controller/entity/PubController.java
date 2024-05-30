package com.alcoholchat.controller.entity;

import com.alcoholchat.domain.dto.PubDTO;
import com.alcoholchat.domain.dto.SuccessResult;
import com.alcoholchat.domain.entity.Pub;
import com.alcoholchat.service.PubService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/pub")
public class PubController {
    private final PubService pubService;

    @PostMapping
    public ResponseEntity<SuccessResult> savePub(
            @ModelAttribute PubDTO.Request request
    ) {
        String memberEmail = "user1@example.com";   // TODO : 사용자 이메일로 변경
        Pub pub = pubService.savePub(memberEmail, request);
        if (pub != null) {
            return ResponseEntity.status(HttpStatus.CREATED).body(new SuccessResult("Pub save success", "주점 정보가 저장되었습니다."));
        } else {
            throw new IllegalArgumentException("주점 데이터 저장 중 문제가 발생했습니다.");
        }
    }

    @PutMapping("/{pub_id}")
    public ResponseEntity<SuccessResult> updatePub(
            @PathVariable UUID pub_id,
            @ModelAttribute PubDTO.Request request
    ) {
        String memberEmail = "user1@example.com";   // TODO : 사용자 이메일로 변경
        Pub pub = pubService.updatePub(pub_id, request);
        if (pub != null) {
            if (memberEmail.equals(pub.getMember().getEmail())) {
                return ResponseEntity.status(HttpStatus.CREATED).body(new SuccessResult("Pub update success", "주점 정보가 수정되었습니다."));
            } else {
                // 403에러 (회원 불일치)
                return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
            }
        } else {
            throw new IllegalArgumentException("주점 데이터 저장 중 문제가 발생했습니다.");
        }
    }
}
