package com.example.board.post.dto.request;

import com.example.board.comment.entity.Comment;
import com.example.board.post.entity.Post;
import com.example.board.reply.entity.Reply;
import java.util.List;

public record PostOneRequest(
        String title,
        String content,
        String nickname,
        List<Comment> comments
) {
    public static PostOneRequest fromEntity(Post post) {
        return new PostOneRequest(
                post.title,
                post.content,
                post.nickname,
                post.comments // 생각
        );
    }
}
