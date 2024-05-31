package com.alcoholchat.repository.queries.implement;

import com.alcoholchat.repository.queries.ChatQueryRepository;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import org.springframework.stereotype.Repository;

@Repository
public class ChatQueryRepositoryImpl implements ChatQueryRepository {
    private final JPAQueryFactory queryFactory;

    public ChatQueryRepositoryImpl(EntityManager entityManager) {
        this.queryFactory = new JPAQueryFactory(entityManager);
    }
}
