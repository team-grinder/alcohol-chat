package com.alcoholchat.repository;

import com.alcoholchat.domain.entity.Board;
import com.alcoholchat.repository.queries.BoardQueryRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface BoardRepository extends JpaRepository<Board, UUID>, BoardQueryRepository {
}
