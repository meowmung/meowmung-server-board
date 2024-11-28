package com.example.board.post.dto.request;

import com.example.board.comment.dto.request.CommentOneRequest;
import com.example.board.post.entity.Post;
import jakarta.persistence.Column;
import java.util.Date;
import java.util.List;

public record PostOneRequest(
        Long postId,
        String title,
        String content,
        String nickname,
        Date createdAt,
        Integer viewCount,
        List<CommentOneRequest> comments
) {
    public static PostOneRequest fromEntity(Post post) {
        List<CommentOneRequest> commentOneRequests = CommentOneRequest.fromEntity(post.getComments());

        return new PostOneRequest(
                post.postId,
                post.title,
                post.content,
                post.nickname,
                post.createdAt,
                post.viewCount,
                commentOneRequests // 생각
        );
    }
}
