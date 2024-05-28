package com.alcoholchat.domain.entity;

import com.github.f4b6a3.uuid.UuidCreator;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Table(name = "comment")
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Comment extends BaseEntity {

    @Id
    @Lob
    @Column(name = "comment_id", updatable = false)
    private UUID commentId;

    @ManyToOne
    @JoinColumn(name = "parent_comment_id")
    private Comment parentComment;

    @ManyToOne
    @JoinColumn(name = "board_id", nullable = false)
    private Board board;

    @ManyToOne
    @JoinColumn(name = "member_id", nullable = false)
    private Member member;

    @Column(name = "content", nullable = false, length = 255)
    private String content;

    @Column(name = "is_deleted", nullable = false)
    private Boolean isDeleted;

    @PrePersist
    public void prePersist() {
        commentId = UuidCreator.getTimeOrdered();
        isDeleted = isDeleted == null ? false : isDeleted;
    }
}
