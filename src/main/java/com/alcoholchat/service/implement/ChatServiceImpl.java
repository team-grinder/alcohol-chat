package com.alcoholchat.service.implement;

import com.alcoholchat.domain.dto.ChatDTO;
import com.alcoholchat.domain.entity.Chat;
import com.alcoholchat.service.ChatService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ChatServiceImpl implements ChatService {
    @Override
    public Chat saveChat(ChatDTO.Request request) {
        return null;
    }

    @Override
    public Chat findChat(UUID chatId) {
        return null;
    }

    @Override
    public List<Chat> findChatList() {
        return List.of();
    }

    @Override
    public Chat updateChat(ChatDTO.Request request) {
        return null;
    }

    @Override
    public void deleteChat(UUID chatId) {

    }
}
