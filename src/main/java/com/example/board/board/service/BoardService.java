package com.example.board.board.service;

import com.example.board.board.dto.response.BoardResponse;
import com.example.board.board.entity.Board;
import com.example.board.board.repository.BoardRepository;
import com.example.board.post.entity.Post;
import com.example.board.post.repository.PostRepository;
import java.util.Collections;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BoardService {
    private final PostRepository postRepository;
    private final BoardRepository boardRepository;

    public List<BoardResponse> findAllByBoardCategory(String boardCategory) {
        Board board = boardRepository.findAllByBoardCategory(boardCategory)
                .orElseThrow(() -> new IllegalArgumentException("해당 카테고리가 존재하지 않습니다."));
        return Collections.singletonList(BoardResponse.fromEntity(board));
    }

    public Page<Post> getPostsByPage(String boardCategory, int page) {

    }


}
