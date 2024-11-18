package com.example.board.post.dto.response;

import com.example.board.common.BoardCategory;
import java.util.Date;

public record PostResponse(
        Long postId,
        String title,
        String content,
        BoardCategory boardCategory,
        Date createdAt,
        Date updatedAt,
        Integer viewCount,
        Integer complainCount
) {
}
