package com.alcoholchat.domain.entity;

import com.alcoholchat.domain.enums.Role;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "member", indexes = {
        @Index(name = "idx_role", columnList = "role")
})
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Member extends BaseEntity{

    @Id
    @Column(name = "member_id", updatable = false, length = 36)
    private String memberId;

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
        memberId = memberId == null ? UUID.randomUUID().toString() : memberId;
        role = role == null ? Role.MEMBER : role;
        isDeleted = isDeleted == null ? false : isDeleted;
    }
}
