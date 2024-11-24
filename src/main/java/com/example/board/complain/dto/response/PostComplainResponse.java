package com.example.board.complain.dto.response;

import com.example.board.complain.entity.PostComplain;
import java.util.Date;

public record PostComplainResponse(
        Long postComplainId,
        String postComplainContent,
        Date createdAt,
        Long postId,
        String title,
        String content
) {
    public static PostComplainResponse fromEntity(PostComplain postComplain) {
        return new PostComplainResponse(
                postComplain.getPostComplainId(),
                postComplain.getPostComplainContent(),
                postComplain.getCreatedAt(),
                postComplain.getPost().getPostId(),
                postComplain.getPost().getTitle(),
                postComplain.getPost().getContent()
        );
    }
}
