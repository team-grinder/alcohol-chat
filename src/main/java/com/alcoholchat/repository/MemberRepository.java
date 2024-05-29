package com.alcoholchat.repository;

import com.alcoholchat.domain.entity.Member;
import com.alcoholchat.repository.queries.MemberQueryRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface MemberRepository extends JpaRepository<Member, UUID>, MemberQueryRepository {

    Optional<Member> findByEmail(String email);

}
