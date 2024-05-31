package com.alcoholchat.repository;

import com.alcoholchat.domain.entity.Member;
import com.alcoholchat.domain.entity.Pub;
import com.alcoholchat.domain.enums.Role;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import jakarta.persistence.EntityManager;
import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@ActiveProfiles("test")
class PubRepositoryTest {

    @Autowired
    private PubRepository pubRepository;

    @Autowired
    private EntityManager entityManager;

    private Member member;
    private Pub pub;

    @BeforeEach
    void setUp() {
        member = Member.builder()
                       .email("test@example.com")
                       .password("password")
                       .nickname("nickname")
                       .phoneNum("01012345678")
                       .preferLocal("local")
                       .birth(LocalDate.of(1990, 1, 1))
                       .role(Role.MEMBER)
                       .isDeleted(false)
                       .build();

        entityManager.persist(member);
        entityManager.flush();

        pub = Pub.builder()
                 .member(member)
                 .name("Test Pub")
                 .address("123 Test Street")
                 .description("A test description")
                 .isDeleted(false)
                 .build();

        pubRepository.save(pub);
    }

    @AfterEach
    void tearDown() {
        pubRepository.deleteAll();
        entityManager.clear();
    }

    @Test
    void findByIsDeletedFalse() {
        // given: isDeleted가 true인 pub 객체를 추가
        Pub deletedPub = Pub.builder()
                            .member(member)
                            .name("Deleted Pub")
                            .address("123 Deleted Street")
                            .description("A deleted description")
                            .isDeleted(true)
                            .build();

        pubRepository.save(deletedPub);

        // when: findByIsDeletedFalse 호출
        List<Pub> pubs = pubRepository.findByIsDeletedFalse();

        // then: isDeleted가 false인 pub 객체만 반환되었는지 확인
        assertFalse(pubs.isEmpty());
        assertTrue(pubs.contains(pub));
        assertFalse(pubs.contains(deletedPub));
    }
}
