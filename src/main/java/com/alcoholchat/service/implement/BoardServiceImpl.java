package com.alcoholchat.service.implement;

import com.alcoholchat.domain.dto.BoardDTO;
import com.alcoholchat.domain.entity.Board;
import com.alcoholchat.service.BoardService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class BoardServiceImpl implements BoardService {
    @Override
    public Board saveBoard(BoardDTO.Request request) {
        return null;
    }

    @Override
    public Board findBoard(UUID boardId) {
        return null;
    }

    @Override
    public List<Board> findBoardList() {
        return List.of();
    }

    @Override
    public Board updateBoard(BoardDTO.Request request) {
        return null;
    }

    @Override
    public void deleteBoard(UUID boardId) {

    }
}
