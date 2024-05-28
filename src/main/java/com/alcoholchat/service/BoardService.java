package com.alcoholchat.service;

import com.alcoholchat.domain.dto.BoardDTO;
import com.alcoholchat.domain.entity.Board;

import java.util.List;
import java.util.UUID;

public interface BoardService {
    // create
    Board saveBoard(BoardDTO.Request request);
    // read
    Board findBoard(UUID boardId);
    List<Board> findBoardList();
    // update
    Board updateBoard(BoardDTO.Request request);
    // delete
    void deleteBoard(UUID boardId);
}
