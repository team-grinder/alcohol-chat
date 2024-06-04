package com.alcoholchat.controller.handler;

import com.alcoholchat.domain.dto.PubDTO;
import com.alcoholchat.domain.dto.SuccessResult;
import com.alcoholchat.domain.entity.Pub;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

public class PubApiResponseHandler {
    public ResponseEntity<SuccessResult> apiReturn(
            Pub pub,
            String memberEmail,
            String successCode,
            String successMessage,
            String errorMessage
    ) {
        if (pub != null) {
            if (memberEmail.equals(pub.getMember().getEmail())) {
                return ResponseEntity.status(HttpStatus.CREATED).body(new SuccessResult(successCode, successMessage));
            } else {
                // 403에러 (회원 불일치)
                return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
            }
        } else {
            throw new IllegalArgumentException(errorMessage);
        }
    }

    public ResponseEntity<PubDTO.Response> apiReturn(
            Pub pub,
            String errorMessage
    ) {
        if (pub != null) {
            PubDTO.Response response = new PubDTO.Response(pub);
            return ResponseEntity.status(HttpStatus.OK).body(response);
        } else {
            throw new IllegalArgumentException(errorMessage);
        }
    }

    public ResponseEntity<List<PubDTO.Response>> apiReturn(
            List<Pub> pubList,
            String errorMessage
    ) {
        if (pubList != null) {
            List<PubDTO.Response> responseList = pubList.stream().map(PubDTO.Response::new).toList();
            return ResponseEntity.status(HttpStatus.OK).body(responseList);
        } else {
            throw new IllegalArgumentException(errorMessage);
        }
    }
}
