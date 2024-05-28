package com.alcoholchat.service.implement;

import com.alcoholchat.domain.dto.PubDTO;
import com.alcoholchat.domain.entity.Pub;
import com.alcoholchat.service.PubService;

import java.util.List;
import java.util.UUID;

public class PubServiceImpl implements PubService {
    @Override
    public Pub savePub(PubDTO.Request request) {
        return null;
    }

    @Override
    public Pub findPub(UUID pubId) {
        return null;
    }

    @Override
    public List<Pub> findPubList() {
        return List.of();
    }

    @Override
    public Pub updatePub(PubDTO.Request request) {
        return null;
    }

    @Override
    public void deletePub(UUID pubId) {

    }
}
