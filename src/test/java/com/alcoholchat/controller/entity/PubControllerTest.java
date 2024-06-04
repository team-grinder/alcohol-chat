package com.alcoholchat.controller.entity;

import com.alcoholchat.controller.handler.PubApiResponseHandler;
import com.alcoholchat.domain.dto.PubDTO;
import com.alcoholchat.domain.dto.SuccessResult;
import com.alcoholchat.domain.entity.Member;
import com.alcoholchat.domain.entity.Pub;
import com.alcoholchat.service.PubService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class PubControllerTest {

    private MockMvc mockMvc;

    @Mock
    private PubService pubService;

    @Mock
    private PubApiResponseHandler responseHandler;

    @InjectMocks
    private PubController pubController;

    private Member member;
    private Pub pub;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(pubController).build();

        member = Member.builder()
                       .email("user1@example.com")
                       .password("password")
                       .nickname("nickname")
                       .phoneNum("1234567890")
                       .preferLocal("local")
                       .birth(LocalDate.now())
                       .build();
        member.prePersist();

        pub = Pub.builder()
                 .member(member)
                 .name("Test Pub")
                 .address("123 Test Street")
                 .description("A place to test")
                 .build();
        pub.prePersist();
    }

    @Test
    void savePub() throws Exception {
        // Given
        SuccessResult successResult = new SuccessResult("Pub save success", "주점 정보가 저장되었습니다.");

        when(pubService.savePub(anyString(), any(PubDTO.Request.class))).thenReturn(pub);
        when(responseHandler.pubApiReturn(any(Pub.class), anyString(), anyString(), anyString(), anyString()))
                .thenReturn(ResponseEntity.status(HttpStatus.CREATED).body(successResult));

        // When
        mockMvc.perform(post("/pub")
                                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                                .param("name", "Test Pub")
                                .param("address", "123 Test Street")
                                .param("description", "A place to test"))
               // Then
               .andExpect(status().isCreated())
               .andExpect(jsonPath("$.message", is("주점 정보가 저장되었습니다.")));
    }

    @Test
    void updatePub() throws Exception {
        // Given
        Pub updatedPub = Pub.builder()
                            .pubId(pub.getPubId())
                            .member(member)
                            .name("Updated Pub")
                            .address("456 Updated Street")
                            .description("An updated place to test")
                            .build();
        updatedPub.prePersist();
        SuccessResult successResult = new SuccessResult("Pub update success", "주점 정보가 수정되었습니다.");

        when(pubService.updatePub(any(UUID.class), any(PubDTO.Request.class))).thenReturn(updatedPub);
        when(responseHandler.pubApiReturn(any(Pub.class), anyString(), anyString(), anyString(), anyString()))
                .thenReturn(ResponseEntity.status(HttpStatus.CREATED).body(successResult));

        // When
        mockMvc.perform(put("/pub/{pub_id}", pub.getPubId())
                                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                                .param("name", "Updated Pub")
                                .param("address", "456 Updated Street")
                                .param("description", "An updated place to test"))
               // Then
               .andExpect(status().isCreated())
               .andExpect(jsonPath("$.message", is("주점 정보가 수정되었습니다.")));
    }

    @Test
    void deletePub() throws Exception {
        // Given
        SuccessResult successResult = new SuccessResult("Pub delete success", "주점 정보가 삭제되었습니다.");

        when(pubService.deletePub(any(UUID.class))).thenReturn(pub);
        when(responseHandler.pubApiReturn(any(Pub.class), anyString(), anyString(), anyString(), anyString()))
                .thenReturn(ResponseEntity.status(HttpStatus.CREATED).body(successResult));

        // When
        mockMvc.perform(delete("/pub/{pub_id}", pub.getPubId()))
               // Then
               .andExpect(status().isCreated())
               .andExpect(jsonPath("$.message", is("주점 정보가 삭제되었습니다.")));
    }

    @Test
    void getPub() throws Exception {
        // Given
        PubDTO.Response response = new PubDTO.Response(pub);

        when(pubService.findPub(any(UUID.class))).thenReturn(pub);
        when(responseHandler.pubApiReturn(any(Pub.class), anyString()))
                .thenReturn(ResponseEntity.ok(response));

        // When
        mockMvc.perform(get("/pub/{pub_id}", pub.getPubId()))
               // Then
               .andExpect(status().isOk())
               .andExpect(jsonPath("$.name", is("Test Pub")))
               .andExpect(jsonPath("$.address", is("123 Test Street")))
               .andExpect(jsonPath("$.description", is("A place to test")));
    }

    @Test
    void getAllPub() throws Exception {
        // Given
        Pub pub2 = Pub.builder()
                      .member(member)
                      .name("Test Pub 2")
                      .address("456 Test Avenue")
                      .description("Another place to test")
                      .build();
        pub2.prePersist();
        List<Pub> pubList = Arrays.asList(pub, pub2);
        PubDTO.Response response1 = new PubDTO.Response(pub);
        PubDTO.Response response2 = new PubDTO.Response(pub2);
        List<PubDTO.Response> responseList = Arrays.asList(response1, response2);

        when(pubService.findPubList()).thenReturn(pubList);
        when(responseHandler.pubApiReturn(any(List.class), anyString()))
                .thenReturn(ResponseEntity.ok(responseList));

        // When
        mockMvc.perform(get("/pub/allpub"))
               // Then
               .andExpect(status().isOk())
               .andExpect(jsonPath("$[0].name", is("Test Pub")))
               .andExpect(jsonPath("$[1].name", is("Test Pub 2")));
    }
}
