package com.alcoholchat.domain.entity;

import com.alcoholchat.domain.dto.PubDTO;
import com.alcoholchat.domain.enums.Role;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import jakarta.persistence.EntityManager;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@ActiveProfiles("test")
class PubTest {

    @Autowired
    private EntityManager entityManager;

    private Pub pub;
    private Member member;

    @BeforeEach
    void setUp() {
        // given: 테스트에 필요한 객체 생성
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

        // Member 엔티티 영속화
        entityManager.persist(member);
        entityManager.flush();

        pub = Pub.builder()
                 .member(member)
                 .name("Test Pub")
                 .address("123 Test Street")
                 .description("A test description")
                 .isDeleted(false)
                 .build();

        // Pub 엔티티 영속화
        entityManager.persist(pub);
        entityManager.flush();
    }

    @AfterEach
    void tearDown() {
        entityManager.clear();
    }

    @Test
    void getCreatedAt() {
        // when: createdAt 호출
        // then: createdAt 값이 null이 아님을 확인
        assertNotNull(pub.getCreatedAt());
    }

    @Test
    void getUpdatedAt() {
        // when: updatedAt 호출
        // then: updatedAt 값이 null이 아님을 확인
        assertNotNull(pub.getUpdatedAt());
    }

    @Test
    void prePersist() {
        // given: 새로운 pub 객체
        Pub newPub = new Pub();

        // when: prePersist 호출
        newPub.prePersist();

        // then: pubId가 null이 아님을 확인
        assertNotNull(newPub.getPubId());

        // then: isDeleted가 false임을 확인
        assertFalse(newPub.getIsDeleted());
    }

    @Test
    void update() {
        // given: 업데이트할 DTO 생성
        PubDTO.Request request = PubDTO.Request.builder()
                                               .name("Updated Pub")
                                               .address("456 Updated Street")
                                               .description("Updated description")
                                               .build();

        // when: pub 객체 업데이트
        pub.update(request);

        // then: pub 객체의 필드가 업데이트된 값을 가지는지 확인
        assertEquals("Updated Pub", pub.getName());
        assertEquals("456 Updated Street", pub.getAddress());
        assertEquals("Updated description", pub.getDescription());
    }

    @Test
    void delete() {
        // given: 삭제되지 않은 pub 객체
        assertFalse(pub.getIsDeleted());

        // when: pub 객체 삭제
        pub.delete();

        // then: pub 객체의 isDeleted 필드가 true인지 확인
        assertTrue(pub.getIsDeleted());
    }

    @Test
    void getPubId() {
        // given: pub 객체 생성
        // when: pubId 호출
        // then: pubId 값이 null이 아님을 확인
        assertNotNull(pub.getPubId());
    }

    @Test
    void getMember() {
        // given: pub 객체 생성
        // when: member 호출
        // then: member 값이 null이 아님을 확인
        assertNotNull(pub.getMember());
    }

    @Test
    void getName() {
        // given: pub 객체 생성
        // when: name 호출
        // then: name 값이 예상된 값과 일치하는지 확인
        assertEquals("Test Pub", pub.getName());
    }

    @Test
    void getAddress() {
        // given: pub 객체 생성
        // when: address 호출
        // then: address 값이 예상된 값과 일치하는지 확인
        assertEquals("123 Test Street", pub.getAddress());
    }

    @Test
    void getDescription() {
        // given: pub 객체 생성
        // when: description 호출
        // then: description 값이 예상된 값과 일치하는지 확인
        assertEquals("A test description", pub.getDescription());
    }

    @Test
    void getIsDeleted() {
        // given: pub 객체 생성
        // when: isDeleted 호출
        // then: isDeleted 값이 예상된 값과 일치하는지 확인
        assertFalse(pub.getIsDeleted());
    }

    @Test
    void builder() {
        // given: builder를 사용해 pub 객체 생성
        // when: pub 객체가 잘 생성되는지 확인
        Pub builtPub = Pub.builder()
                          .member(member)
                          .name("Built Pub")
                          .address("789 Built Street")
                          .description("Built description")
                          .isDeleted(false)
                          .build();

        // then: 각 필드가 예상된 값과 일치하는지 확인
        assertNotNull(builtPub);
        assertEquals("Built Pub", builtPub.getName());
        assertEquals("789 Built Street", builtPub.getAddress());
        assertEquals("Built description", builtPub.getDescription());
        assertFalse(builtPub.getIsDeleted());
    }
}
