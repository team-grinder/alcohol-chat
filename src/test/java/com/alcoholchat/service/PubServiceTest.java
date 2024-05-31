package com.alcoholchat.service;

import com.alcoholchat.domain.dto.PubDTO;
import com.alcoholchat.domain.entity.Member;
import com.alcoholchat.domain.entity.Pub;
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

    private Member member;
    private Pub pub;
    private PubDTO.Request request;
    private UUID pubId;

    @BeforeEach
    void setUp() {
        closeable = MockitoAnnotations.openMocks(this);

        member = Member.builder()
                       .email("test@example.com")
                       .password("password")
                       .nickname("nickname")
                       .phoneNum("01012345678")
                       .preferLocal("local")
                       .birth(LocalDate.of(1990, 1, 1))
                       .build();

        pubId = UUID.randomUUID();
        pub = Pub.builder()
                 .pubId(pubId)
                 .member(member)
                 .name("Test Pub")
                 .address("123 Test Street")
                 .description("A test description")
                 .isDeleted(false)
                 .build();

        request = PubDTO.Request.builder()
                                .name("Test Pub")
                                .address("123 Test Street")
                                .description("A test description")
                                .build();
    }

    @AfterEach
    void tearDown() throws Exception {
        closeable.close();
    }

    @Test
    void savePub() {
        // Given
        when(memberService.findMemberByEmail(anyString())).thenReturn(member);
        when(pubRepository.save(any(Pub.class))).thenAnswer(invocation -> {
            Pub pub = invocation.getArgument(0);
            pub.prePersist(); // Simulate @PrePersist
            return pub;
        });

        // When
        Pub savedPub = pubService.savePub(member.getEmail(), request);

        // Then
        assertNotNull(savedPub);
        assertEquals(request.getName(), savedPub.getName());
        assertEquals(request.getAddress(), savedPub.getAddress());
        assertEquals(request.getDescription(), savedPub.getDescription());

        verify(memberService, times(1)).findMemberByEmail(anyString());
        verify(pubRepository, times(1)).save(any(Pub.class));
    }

    @Test
    void findPub() {
        // Given
        when(pubRepository.findById(pubId)).thenReturn(Optional.of(pub));

        // When
        Pub foundPub = pubService.findPub(pubId);

        // Then
        assertNotNull(foundPub);
        assertEquals(pubId, foundPub.getPubId());
        assertEquals(pub.getName(), foundPub.getName());
        assertEquals(pub.getAddress(), foundPub.getAddress());
        assertEquals(pub.getDescription(), foundPub.getDescription());

        verify(pubRepository, times(1)).findById(pubId);
    }

    @Test
    void findPubList() {
        // Given
        when(pubRepository.findByIsDeletedFalse()).thenReturn(Collections.singletonList(pub));

        // When
        List<Pub> pubs = pubService.findPubList();

        // Then
        assertNotNull(pubs);
        assertFalse(pubs.isEmpty());
        assertEquals(1, pubs.size());
        assertEquals(pub.getName(), pubs.get(0).getName());

        verify(pubRepository, times(1)).findByIsDeletedFalse();
    }

    @Test
    void updatePub() {
        // Given
        Pub existingPub = Pub.builder()
                             .pubId(pubId)
                             .member(member)
                             .name("Old Pub")
                             .address("Old Address")
                             .description("Old Description")
                             .isDeleted(false)
                             .build();

        PubDTO.Request updateRequest = PubDTO.Request.builder()
                                                     .name("Updated Pub")
                                                     .address("Updated Address")
                                                     .description("Updated Description")
                                                     .build();

        when(pubRepository.findById(pubId)).thenReturn(Optional.of(existingPub));
        when(pubRepository.save(any(Pub.class))).thenAnswer(invocation -> {
            Pub pub = invocation.getArgument(0);
            pub.prePersist(); // Simulate @PrePersist
            return pub;
        });

        // When
        Pub updatedPub = pubService.updatePub(pubId, updateRequest);

        // Then
        assertNotNull(updatedPub);
        assertEquals(updateRequest.getName(), updatedPub.getName());
        assertEquals(updateRequest.getAddress(), updatedPub.getAddress());
        assertEquals(updateRequest.getDescription(), updatedPub.getDescription());

        verify(pubRepository, times(1)).findById(pubId);
        verify(pubRepository, times(1)).save(any(Pub.class));
    }

    @Test
    void deletePub() {
        // Given
        when(pubRepository.findById(pubId)).thenReturn(Optional.of(pub));
        when(pubRepository.save(any(Pub.class))).thenAnswer(invocation -> {
            Pub pub = invocation.getArgument(0);
            pub.prePersist(); // Simulate @PrePersist
            return pub;
        });

        // When
        Pub deletedPub = pubService.deletePub(pubId);

        // Then
        assertNotNull(deletedPub);
        assertTrue(deletedPub.getIsDeleted());

        verify(pubRepository, times(1)).findById(pubId);
        verify(pubRepository, times(1)).save(any(Pub.class));
    }
}
