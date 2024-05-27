package com.alcoholchat.domain.entity;

import com.github.f4b6a3.uuid.UuidCreator;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Table(name = "pub")
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Pub extends BaseEntity {

    @Id
    @Lob
    @Column(name = "pub_id", updatable = false)
    private UUID pubId;

    @Column(name = "name", nullable = false, unique = true, length = 50)
    private String name;

    @Column(name = "address", nullable = false, length = 100)
    private String address;

    @Column(name = "description", nullable = false, length = 150)
    private String description;

    @Column(name = "phone_num", nullable = false, length = 11)
    private String phoneNum;

    @PrePersist
    public void prePersist() {
        pubId = UuidCreator.getTimeOrdered();
    }
}
