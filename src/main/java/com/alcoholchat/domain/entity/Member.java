package com.alcoholchat.domain.entity;

import com.alcoholchat.domain.enums.Role;
import com.github.f4b6a3.uuid.UuidCreator;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "member", indexes = {@Index(name = "idx_role", columnList = "role")})
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Member extends BaseEntity {

    @Id
    @Lob
    @Column(name = "member_id", updatable = false)
    private UUID memberId;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "nickname", nullable = false, unique = true)
    private String nickname;

    @Column(name = "phone_num", nullable = false, length = 11)
    private String phoneNum;

    @Column(name = "prefer_local", length = 30)
    private String preferLocal;

    @Column(name = "birth", nullable = false, length = 30)
    private LocalDate birth;

    @Enumerated(EnumType.STRING)
    @Column(name = "role", nullable = false, length = 16)
    private Role role;

    @Column(name = "is_deleted", nullable = false)
    private Boolean isDeleted;

    @PrePersist
    public void prePersist() {
        memberId = UuidCreator.getTimeOrdered();
        role = role == null ? Role.MEMBER : role;
        isDeleted = isDeleted == null ? false : isDeleted;
    }
}
