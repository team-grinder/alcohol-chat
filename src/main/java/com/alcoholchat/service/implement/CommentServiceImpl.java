package com.alcoholchat.service.implement;

import com.alcoholchat.domain.dto.CommentDTO;
import com.alcoholchat.domain.entity.Comment;
import com.alcoholchat.service.CommentService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class CommentServiceImpl implements CommentService {
    @Override
    public Comment saveComment(CommentDTO.Request request) {
        return null;
    }

    @Override
    public Comment findComment(UUID commentId) {
        return null;
    }

    @Override
    public List<Comment> findCommentList() {
        return List.of();
    }

    @Override
    public Comment updateComment(CommentDTO.Request request) {
        return null;
    }

    @Override
    public void deleteComment(UUID commentId) {

    }
}
