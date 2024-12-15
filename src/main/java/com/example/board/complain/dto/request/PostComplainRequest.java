package com.example.board.complain.dto.request;

import com.example.board.complain.entity.PostComplain;
import com.example.board.post.entity.Post;
//
//public record PostComplainRequest(
//        String postComplainContent
//) {
//    public PostComplain toEntity(Post post){
//        return PostComplain.builder()
//                .postComplainContent(postComplainContent)
//                .post(post)
//                .build();
//    }
//}

public record PostComplainRequest(
        Long postId
) {

    public static PostComplain toEntity(Post post){
        return PostComplain.builder()
                .post(post)
                .build();
    }
}
