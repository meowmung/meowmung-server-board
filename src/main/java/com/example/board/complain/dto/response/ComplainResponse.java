package com.example.board.complain.dto.response;

import java.util.Date;

public record ComplainResponse (
        Long complainId,
        String complainContent,
        Date createdAt,
        Long postId,
        String content

) {
}
