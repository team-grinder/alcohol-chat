package com.alcoholchat.domain.dto;


import lombok.Getter;
import lombok.Setter;
import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

public class MemberDTO {
  
    @Getter
    @Setter
    @NoArgsConstructor
    public static class SignUpDTO {
        private String email;
        private String password;
        private String nickname;
        private String phoneNum;
        private LocalDate birth;
    }

    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Request {
        private String id;
    }

    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Response {
        private String id;
    }
}
