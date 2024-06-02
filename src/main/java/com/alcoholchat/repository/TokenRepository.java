package com.alcoholchat.repository;

import com.alcoholchat.domain.entity.Token;
import org.springframework.data.repository.CrudRepository;

public interface TokenRepository extends CrudRepository<Token, String> {
}
