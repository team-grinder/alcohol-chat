package com.alcoholchat.repository.queries.implement;

import com.alcoholchat.repository.queries.PubQueryRepository;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import org.springframework.stereotype.Repository;

@Repository
public class PubQueryRepositoryImpl implements PubQueryRepository {
    private final JPAQueryFactory queryFactory;

    public PubQueryRepositoryImpl(EntityManager entityManager) {
        this.queryFactory = new JPAQueryFactory(entityManager);
    }
}
