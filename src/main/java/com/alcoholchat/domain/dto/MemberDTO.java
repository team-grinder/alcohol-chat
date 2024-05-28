package com.alcoholchat.domain.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

public class MemberDTO {
    @Getter
    @Setter
    @NoArgsConstructor
    public static class signUpDTO {
        private String email;
        private String password;
        private String nickname;
        private String phoneNum;
        private LocalDate birth;
    }
}
