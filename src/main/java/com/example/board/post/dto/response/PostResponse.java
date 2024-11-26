package com.example.board.post.dto.response;

import com.example.board.post.entity.Post;
import java.util.Date;
import lombok.Builder;

@Builder
public record PostResponse(
        Long postId,
        String title,
        Date createdAt,
        Date updatedAt,
        Integer viewCount,
        String nickname
) {
    public static PostResponse fromEntity(Post post) {
        return PostResponse.builder()
                .postId(post.getPostId()) // Post 엔티티의 필드명에 맞게 호출
                .title(post.getTitle())
                .createdAt(post.getCreatedAt())
                .updatedAt(post.getUpdatedAt())
                .viewCount(post.getViewCount())
                .nickname(post.getNickname())
                .build();
    }


}
