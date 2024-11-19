package com.example.board.complain.service;

import com.example.board.comment.entity.Comment;
import com.example.board.complain.dto.request.PostComplainRequest;
import com.example.board.complain.dto.response.CommentComplainResponse;
import com.example.board.complain.dto.response.PostComplainResponse;
import com.example.board.complain.entity.CommentComplain;
import com.example.board.complain.entity.PostComplain;
import com.example.board.post.entity.Post;
import com.example.board.post.service.PostService;
import com.example.board.complain.repository.PostComplainRepository;
import com.example.board.post.repository.PostRepository;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PostComplainService {
    private final PostComplainRepository postComplainRepository;
    private final PostRepository postRepository;
    private final PostService postService;

    public PostComplainResponse savePostComplain(Long postId, PostComplainRequest postComplainRequest) {
        PostComplain postComplain = postComplainRequest.toEntity(findByPostId(postId));
        postComplainRepository.save(postComplain);
//        incrementComplainCount(postComplain);
        incrementPostComplainCount(postId);
        postService.checkAndDeletePost(postId);
        return PostComplainResponse.fromEntity(postComplain);
    }

    public Post findByPostId(Long postId) {
        return postRepository.findByPostId(postId)
                .orElseThrow(() -> new IllegalArgumentException("Post not found with id: " + postId));
    }

    public List<PostComplainResponse> getAllPostComplain(Long postId) {
        Post post = findByPostId(postId);
        List<PostComplain> getComplains = post.getPostComplains();
        return getComplains.stream()
                .map(PostComplainResponse::fromEntity)
                .collect(Collectors.toList());
    }

    // 게시글에서 신고 횟수를 가지고 있는 경우
    public void incrementPostComplainCount(Long postId) {
        Post post = findByPostId(postId);
        post.setPostComplainCount(post.getPostComplainCount() + 1);
        postRepository.save(post);
    }

    // 신고 횟수 증가
    // 신고 테이블에서 신고 횟수를 가지고 있는 경우
//    public void incrementPostComplainCount(PostComplain postComplain) {
//        postComplain.setPostComplainCount(postComplain.getPostComplainCount() + 1);
//        postComplainRepository.save(postComplain);
//    }

}
