package com.alcoholchat.service;

import com.alcoholchat.domain.dto.PubDTO;
import com.alcoholchat.domain.entity.Pub;

import java.util.List;
import java.util.UUID;

public interface PubService {
    // create
    Pub savePub(PubDTO.Request request);
    // read
    Pub findPub(UUID pubId);
    List<Pub> findPubList();
    // update
    Pub updatePub(PubDTO.Request request);
    // delete
    void deletePub(UUID pubId);
}
