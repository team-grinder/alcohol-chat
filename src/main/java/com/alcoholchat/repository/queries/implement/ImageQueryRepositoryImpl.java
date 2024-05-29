package com.alcoholchat.repository.queries.implement;

import com.alcoholchat.repository.queries.ImageQueryRepository;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import org.springframework.stereotype.Repository;

@Repository
public class ImageQueryRepositoryImpl implements ImageQueryRepository {
    private final JPAQueryFactory queryFactory;

    public ImageQueryRepositoryImpl(EntityManager entityManager) {
        this.queryFactory = new JPAQueryFactory(entityManager);
    }
}
