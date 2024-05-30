package com.alcoholchat.domain.dto;

import com.alcoholchat.domain.entity.Pub;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

public class PubDTO {
    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Request {
        private String name;
        private String address;
        private String description;
    }

    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Response {
        private String name;
        private String address;
        private String description;
        // TODO : 이미지
        // TODO : 좋아요
        // TODO : 태그
        // TODO : 태그

        Response(Pub pub) {
            this.name = pub.getName();
            this.address = pub.getAddress();
            this.description = pub.getDescription();
        }
    }
}
