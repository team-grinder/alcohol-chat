package com.alcoholchat.repository;

import com.alcoholchat.domain.entity.Pub;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface PubRepository extends JpaRepository<Pub, UUID> {
}
