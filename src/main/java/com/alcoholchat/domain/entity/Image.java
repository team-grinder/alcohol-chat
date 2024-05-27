package com.alcoholchat.domain.entity;

import com.alcoholchat.domain.enums.ContentType;
import com.github.f4b6a3.uuid.UuidCreator;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Table(name = "image", indexes = {
        @Index(name = "idx_image_content_type", columnList = "content_type")
})
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Image {

    @Id
    @Lob
    @Column(name = "imageId", updatable = false)
    private UUID imageId;

    @Column(name = "image_url", nullable = false)
    private String imageUrl;

    @Column(name = "content_id", nullable = false)
    private UUID contentId;

    @Enumerated(EnumType.STRING)
    @Column(name = "content_type", nullable = false, length = 16)
    private ContentType contentType;

    @PrePersist
    public void prePersist() {
        imageId = UuidCreator.getTimeOrdered();
    }
}