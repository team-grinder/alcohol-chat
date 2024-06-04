package com.alcoholchat.controller.entity;

import com.alcoholchat.controller.handler.PubApiResponseHandler;
import com.alcoholchat.domain.dto.PubDTO;
import com.alcoholchat.domain.dto.SuccessResult;
import com.alcoholchat.domain.entity.Pub;
import com.alcoholchat.service.PubService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/pub")
public class PubController {
    private final PubService pubService;
    private final PubApiResponseHandler responseHandler = new PubApiResponseHandler();

    @PostMapping
    public ResponseEntity<SuccessResult> savePub(
            @ModelAttribute PubDTO.Request request
    ) {
        String memberEmail = "user1@example.com";   // TODO : 사용자 이메일로 변경
        Pub pub = pubService.savePub(memberEmail, request);
        return responseHandler.pubApiReturn(
                pub,
                memberEmail,
                "Pub save success",
                "주점 정보가 저장되었습니다.",
                "주점 데이터 저장 중 문제가 발생했습니다."
        );
    }

    @PutMapping("/{pub_id}")
    public ResponseEntity<SuccessResult> updatePub(
            @PathVariable UUID pub_id,
            @ModelAttribute PubDTO.Request request
    ) {
        String memberEmail = "user1@example.com";   // TODO : 사용자 이메일로 변경
        Pub pub = pubService.updatePub(pub_id, request);
        return responseHandler.pubApiReturn(
                pub,
                memberEmail,
                "Pub update success",
                "주점 정보가 수정되었습니다.",
                "주점 데이터 수정 중 문제가 발생했습니다."
        );
    }

    @DeleteMapping("/{pub_id}")
    public ResponseEntity<SuccessResult> deletePub(
            @PathVariable UUID pub_id
    ) {
        String memberEmail = "user1@example.com";   // TODO : 사용자 이메일로 변경
        Pub pub = pubService.deletePub(pub_id);
        return responseHandler.pubApiReturn(
                pub,
                memberEmail,
                "Pub delete success",
                "주점 정보가 삭제되었습니다.",
                "주점 데이터 삭제 중 문제가 발생했습니다."
        );
    }

    @GetMapping("/{pub_id}")
    public ResponseEntity<PubDTO.Response> getPub(
            @PathVariable UUID pub_id
    ) {
        Pub pub = pubService.findPub(pub_id);
        return responseHandler.pubApiReturn(
                pub,
                "주점 조회 중 문제가 발생했습니다."
        );
    }

    @GetMapping("/allpub")
    public ResponseEntity<List<PubDTO.Response>> getAllPub() {
        List<Pub> pubList = pubService.findPubList();
        return responseHandler.pubApiReturn(
                pubList,
                "주점 전체 조회 중 문제가 발생했습니다."
        );
    }
}
