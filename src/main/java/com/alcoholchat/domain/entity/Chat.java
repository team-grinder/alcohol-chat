package com.alcoholchat.domain.entity;

import com.github.f4b6a3.uuid.UuidCreator;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Table(name = "chat")
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Chat extends BaseEntity {

    @Id
    @Lob
    @Column(name = "chat_id", updatable = false)
    private UUID chatId;

    @ManyToOne
    @JoinColumn(name = "member_id", nullable = false)
    private Member member;

    @ManyToOne
    @JoinColumn(name = "pub_id", nullable = false)
    private Pub pub;

    @Column(name = "message", nullable = false)
    private String message;

    @PrePersist
    public void prePersist() {
        chatId = UuidCreator.getTimeOrdered();
    }
}
