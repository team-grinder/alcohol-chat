package com.alcoholchat.domain.entity;

import com.github.f4b6a3.uuid.UuidCreator;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Table(name = "board")
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Board extends BaseEntity {

    @Id
    @Lob
    @Column(name = "board_id", updatable = false)
    private UUID boardId;

    @ManyToOne
    @JoinColumn(name = "pub_id", nullable = false)
    private Pub pub;

    @ManyToOne
    @JoinColumn(name = "member_id", nullable = false)
    private Member member;

    @Column(name = "content", nullable = false, length = 255)
    private String content;

    @Column(name = "is_deleted", nullable = false)
    private Boolean isDeleted;

    @PrePersist
    public void prePersist() {
        boardId = UuidCreator.getTimeOrdered();
        isDeleted = isDeleted == null ? false : isDeleted;
    }
}
