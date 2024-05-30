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
        String memberEmail = "user1@example.com";
        Pub pub = pubService.savePub(memberEmail, request);
        if (pub != null) {
            return ResponseEntity.status(HttpStatus.CREATED).body(new SuccessResult("Pub save success", "술집 정보가 저장되었습니다."));
        } else {
            throw new IllegalArgumentException("술집 데이터 저장 중 문제가 발생했습니다.");
        }
    }
}
