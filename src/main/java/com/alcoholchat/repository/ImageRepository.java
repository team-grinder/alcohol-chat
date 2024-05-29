package com.alcoholchat.repository;

import com.alcoholchat.domain.entity.Image;
import com.alcoholchat.repository.queries.ImageQueryRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ImageRepository extends JpaRepository<Image, UUID>, ImageQueryRepository {
}
