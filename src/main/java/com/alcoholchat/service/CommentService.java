package com.alcoholchat.service;

import com.alcoholchat.domain.dto.CommentDTO;
import com.alcoholchat.domain.entity.Comment;

import java.util.List;
import java.util.UUID;

public interface CommentService {
    // create
    Comment saveComment(CommentDTO.Request request);
    // read
    Comment findComment(UUID commentId);
    List<Comment> findCommentList();
    // update
    Comment updateComment(CommentDTO.Request request);
    // delete
    void deleteComment(UUID commentId);
}
