package com.example.board.complain.dto.response;

import com.example.board.complain.entity.Complain;
import java.util.Date;

public record ComplainResponse (
        Long complainId,
        String complainContent,
        Date createdAt,
        Long postId,
        String content
) {
    public static ComplainResponse fromEntity(Complain complain) {
        return new ComplainResponse(
                complain.getComplainId(),
                complain.getComplainContent(),
                complain.getCreatedAt(),
                complain.getPost().getPostId(),
                complain.getPost().getContent()
        );
    }
}
