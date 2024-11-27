package com.example.board.board.controller;

import com.example.board.board.dto.response.BoardResponse;
import com.example.board.board.repository.BoardRepository;
import com.example.board.board.service.BoardService;
import com.example.board.post.entity.Post;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("boards/{boardCategory}")
@RequiredArgsConstructor
public class BoardController {
    private final BoardService boardService;
    private final BoardRepository boardRepository;

//    @GetMapping
//    public ResponseEntity<List<BoardResponse>> boardList(
//            @PathVariable(name = "boardCategory") String boardCategory) {
//        List<BoardResponse> posts = boardService.findAllByBoardCategory(boardCategory);
//        System.out.println(posts);
//        return ResponseEntity.ok(posts);
//    }

    // 페이징
    @GetMapping("/pages")
    public BoardResponse getPage(@PathVariable(name = "boardCategory") String boardCategory,
                                 Pageable pageable) {
        // 1번부터 10번 게시글 가져오세요
        return boardService.getPostsByPage(boardCategory, pageable);
    }
}
