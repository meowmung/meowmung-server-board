package com.example.board.post.dto.request;

import com.example.board.comment.dto.request.CommentOneRequest;
import com.example.board.post.entity.Post;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public record PostOneResponse(
        String boardCategory,
        Long postId,
        String title,
        String content,
        String nickname,
        Date createdAt,
        Integer viewCount,
        List<CommentOneRequest> comments
) {
    public static PostOneResponse fromEntity(Post post) {
        List<CommentOneRequest> commentOneRequests = new ArrayList<>();

        if (post.getComments() != null) {
            commentOneRequests = CommentOneRequest.fromEntity(post.getComments());
        }
        return new PostOneResponse(
                post.board.getBoardCategory(),
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
