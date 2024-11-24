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
        // bordCategory 값에 free 존재하지 않으면 삽입
        if (boardRepository.findByBoardCategory("free").isEmpty()) {
            boardRepository.save(Board.builder()
                    .boardCategory("free")
                    .build());
        }

        // bordCategory 값에 free 카테고리가 존재하지 않으면 삽입
        if (boardRepository.findByBoardCategory("qna").isEmpty()) {
            boardRepository.save(Board.builder()
                    .boardCategory("qna")
                    .build());
        }
    }
}