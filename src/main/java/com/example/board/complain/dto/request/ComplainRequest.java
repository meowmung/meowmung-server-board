package com.example.board.complain.dto.request;

import com.example.board.complain.entity.Complain;
import com.example.board.post.entity.Post;

public record ComplainRequest (
        String complainContent
) {
    public Complain toEntity(Post post){
        return Complain.builder()
                .complainContent(complainContent)
                .post(post)
                .build();
    }
}
