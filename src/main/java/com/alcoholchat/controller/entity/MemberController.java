package com.alcoholchat.controller.entity;

import com.alcoholchat.domain.dto.MemberDTO;
import com.alcoholchat.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/member")
public class MemberController {

    private final MemberService memberService;

    @PostMapping("/signup")
    public String signup(@RequestBody MemberDTO.SignUpDTO dto) {

        Boolean result = memberService.saveMember(dto);

        if (result) {
            return "signup success";
        } else return "signup failed";
    }
}
