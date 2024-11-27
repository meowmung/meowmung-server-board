package com.example.board.board.service;

import com.example.board.board.dto.response.BoardResponse;
import com.example.board.board.entity.Board;
import com.example.board.board.repository.BoardRepository;
import com.example.board.post.entity.Post;
import com.example.board.post.repository.PostRepository;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BoardService {
    private final PostRepository postRepository;
    private final BoardRepository boardRepository;

//    public List<BoardResponse> findAllByBoardCategory(String boardCategory) {
//        Board board = boardRepository.findById(boardCategory)
//                .orElseThrow(() -> new IllegalArgumentException("해당 카테고리가 존재하지 않습니다."));
//        List<Optional<Post>> allByBoard = postRepository.findAllByBoard(board);
//        return Collections.singletonList(BoardResponse.fromEntity(boardCategory, allByBoard));
//    }

    public BoardResponse getPostsByPage(String boardCategory, Pageable pageable) {
        Page<Post> byCategory = postRepository.findByCategory(boardCategory, pageable);
        return BoardResponse.fromEntity(boardCategory, byCategory);
    }
}
