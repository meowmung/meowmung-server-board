package com.example.board.complain.dto.request;

import com.example.board.complain.entity.PostComplain;
import com.example.board.post.entity.Post;

public record PostComplainRequest(
        String complainContent
) {
    public PostComplain toEntity(Post post){
        return PostComplain.builder()
                .complainContent(complainContent)
                .post(post)
                .build();
    }
}
