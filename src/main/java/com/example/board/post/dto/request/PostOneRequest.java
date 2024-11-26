package com.example.board.post.dto.request;

import com.example.board.comment.dto.request.CommentOneRequest;
import com.example.board.post.entity.Post;
import java.util.List;

public record PostOneRequest(
        String title,
        String content,
        String nickname,
        List<CommentOneRequest> comments
) {
    public static PostOneRequest fromEntity(Post post) {
        List<CommentOneRequest> commentOneRequests = CommentOneRequest.fromEntity(post.getComments());

        return new PostOneRequest(
                post.title,
                post.content,
                post.nickname,
                commentOneRequests // 생각
        );
    }
}
