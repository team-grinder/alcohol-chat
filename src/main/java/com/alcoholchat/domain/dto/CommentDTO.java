package com.alcoholchat.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

public class CommentDTO {
    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Request {}

    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Response {}
}
