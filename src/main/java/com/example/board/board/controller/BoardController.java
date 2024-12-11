package com.example.board.board.controller;

import com.example.board.board.dto.response.BoardResponse;
import com.example.board.board.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("boards/{boardCategory}")
@RequiredArgsConstructor
public class BoardController {
    private final BoardService boardService;

    // 페이징
    // pages?page
    @GetMapping("/pages")
    public BoardResponse getPage(@PathVariable(name = "boardCategory") String boardCategory,
                                 Pageable pageable) {
        // 1번부터 10번 게시글 가져오세요
        return boardService.getPostsByPage(boardCategory, pageable);
    }
}
