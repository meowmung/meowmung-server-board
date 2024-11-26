package com.example.board.board.initializer;

import com.example.board.board.entity.Board;
import com.example.board.board.repository.BoardRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class BoardInitializer {
    private final BoardRepository boardRepository;

    @PostConstruct
    public void init() {
        // bordCategory 값이 존재하지 않으면 삽입
        if (boardRepository.findById("free").isEmpty()) {
            Board free = Board.builder().boardCategory("free").build();
        }
        if (boardRepository.findById("qna").isEmpty()) {
            Board qna = Board.builder().boardCategory("qna").build();
        }
    }
}