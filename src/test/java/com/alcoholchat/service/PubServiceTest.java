package com.alcoholchat.service;

import com.alcoholchat.domain.dto.PubDTO;
import com.alcoholchat.domain.entity.Member;
import com.alcoholchat.domain.entity.Pub;
import com.alcoholchat.domain.enums.Role;
import com.alcoholchat.repository.PubRepository;
import com.alcoholchat.service.implement.PubServiceImpl;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ActiveProfiles("test")
class PubServiceTest {

    @Mock
    private PubRepository pubRepository;

    @Mock
    private MemberService memberService;

    @InjectMocks
    private PubServiceImpl pubService;

    private AutoCloseable closeable;

    @BeforeEach
    void setUp() {
        closeable = MockitoAnnotations.openMocks(this);
    }

    @AfterEach
    void tearDown() throws Exception {
        closeable.close();
    }

    @Test
    void savePub() {
        // given
        String memberEmail = "test@example.com";
        Member member = Member.builder()
                              .email(memberEmail)
                              .password("password")
                              .nickname("nickname")
                              .phoneNum("01012345678")
                              .preferLocal("local")
                              .birth(LocalDate.of(1990, 1, 1))
                              .role(Role.MEMBER)
                              .isDeleted(false)
                              .build();

        PubDTO.Request request = PubDTO.Request.builder()
                                               .name("Test Pub")
                                               .address("123 Test Street")
                                               .description("A test description")
                                               .build();

        when(memberService.findMemberByEmail(anyString())).thenReturn(member);
        when(pubRepository.save(any(Pub.class))).thenAnswer(invocation -> invocation.getArgument(0));

        // when
        Pub savedPub = pubService.savePub(memberEmail, request);

        // then
        assertNotNull(savedPub);
        assertEquals(request.getName(), savedPub.getName());
        assertEquals(request.getAddress(), savedPub.getAddress());
        assertEquals(request.getDescription(), savedPub.getDescription());

        verify(memberService, times(1)).findMemberByEmail(anyString());
        verify(pubRepository, times(1)).save(any(Pub.class));
    }

    @Test
    void findPub() {
        // given
        UUID pubId = UUID.randomUUID();
        Pub pub = Pub.builder()
                     .pubId(pubId)
                     .name("Test Pub")
                     .address("123 Test Street")
                     .description("A test description")
                     .isDeleted(false)
                     .build();

        when(pubRepository.findById(pubId)).thenReturn(Optional.of(pub));

        // when
        Pub foundPub = pubService.findPub(pubId);

        // then
        assertNotNull(foundPub);
        assertEquals(pubId, foundPub.getPubId());
        assertEquals(pub.getName(), foundPub.getName());
        assertEquals(pub.getAddress(), foundPub.getAddress());
        assertEquals(pub.getDescription(), foundPub.getDescription());

        verify(pubRepository, times(1)).findById(pubId);
    }

    @Test
    void findPubList() {
        // given
        Pub pub = Pub.builder()
                     .name("Test Pub")
                     .address("123 Test Street")
                     .description("A test description")
                     .isDeleted(false)
                     .build();

        when(pubRepository.findByIsDeletedFalse()).thenReturn(Collections.singletonList(pub));

        // when
        List<Pub> pubs = pubService.findPubList();

        // then
        assertNotNull(pubs);
        assertFalse(pubs.isEmpty());
        assertEquals(1, pubs.size());
        assertEquals(pub.getName(), pubs.get(0).getName());

        verify(pubRepository, times(1)).findByIsDeletedFalse();
    }

    @Test
    void updatePub() {
        // given
        UUID pubId = UUID.randomUUID();
        Pub existingPub = Pub.builder()
                             .pubId(pubId)
                             .name("Old Pub")
                             .address("Old Address")
                             .description("Old Description")
                             .isDeleted(false)
                             .build();

        PubDTO.Request request = PubDTO.Request.builder()
                                               .name("Updated Pub")
                                               .address("Updated Address")
                                               .description("Updated Description")
                                               .build();

        when(pubRepository.findById(pubId)).thenReturn(Optional.of(existingPub));
        when(pubRepository.save(any(Pub.class))).thenAnswer(invocation -> invocation.getArgument(0));

        // when
        Pub updatedPub = pubService.updatePub(pubId, request);

        // then
        assertNotNull(updatedPub);
        assertEquals(request.getName(), updatedPub.getName());
        assertEquals(request.getAddress(), updatedPub.getAddress());
        assertEquals(request.getDescription(), updatedPub.getDescription());

        verify(pubRepository, times(1)).findById(pubId);
        verify(pubRepository, times(1)).save(any(Pub.class));
    }

    @Test
    void deletePub() {
        // given
        UUID pubId = UUID.randomUUID();
        Pub existingPub = Pub.builder()
                             .pubId(pubId)
                             .name("Test Pub")
                             .address("123 Test Street")
                             .description("A test description")
                             .isDeleted(false)
                             .build();

        when(pubRepository.findById(pubId)).thenReturn(Optional.of(existingPub));
        when(pubRepository.save(any(Pub.class))).thenAnswer(invocation -> invocation.getArgument(0));

        // when
        Pub deletedPub = pubService.deletePub(pubId);

        // then
        assertNotNull(deletedPub);
        assertTrue(deletedPub.getIsDeleted());

        verify(pubRepository, times(1)).findById(pubId);
        verify(pubRepository, times(1)).save(any(Pub.class));
    }
}
