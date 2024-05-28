package com.alcoholchat.service;

import com.alcoholchat.domain.dto.ChatDTO;
import com.alcoholchat.domain.entity.Chat;

import java.util.List;
import java.util.UUID;

public interface ChatService {
    // create
    Chat saveChat(ChatDTO.Request request);
    // read
    Chat findChat(UUID chatId);
    List<Chat> findChatList();
    // update
    Chat updateChat(ChatDTO.Request request);
    // delete
    void deleteChat(UUID chatId);
}
