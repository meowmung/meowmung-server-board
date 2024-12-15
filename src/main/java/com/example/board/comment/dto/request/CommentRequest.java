package com.example.board.comment.dto.request;

import com.example.board.comment.entity.Comment;
import com.example.board.post.entity.Post;

public record CommentRequest(
        String commentContent
) {
    public Comment toEntity(Post post, String nickname, Long memberId) {
        return Comment.builder()
                .commentContent(commentContent)
                .post(post)
                .nickname(nickname)
                .memberId(memberId)
                .build();
    }

}