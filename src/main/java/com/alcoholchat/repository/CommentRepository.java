package com.alcoholchat.repository;

import com.alcoholchat.domain.entity.Comment;
import com.alcoholchat.repository.queries.CommentQueryRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface CommentRepository extends JpaRepository<Comment, UUID>, CommentQueryRepository {
}
