package com.alcoholchat.domain.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ContentType {
    MEMBER("회원"),
    PUB("술집"),
    BOARD("게시글"),
    SELLER_APPLY("판매자신청");

    private final String value;
}