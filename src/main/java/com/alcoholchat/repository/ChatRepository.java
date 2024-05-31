package com.alcoholchat.repository;

import com.alcoholchat.domain.entity.Chat;
import com.alcoholchat.repository.queries.ChatQueryRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ChatRepository extends JpaRepository<Chat, UUID>, ChatQueryRepository {
}
