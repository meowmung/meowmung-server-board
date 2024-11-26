package com.example.board.post.service;

import com.example.board.board.entity.Board;
import com.example.board.board.repository.BoardRepository;
import com.example.board.post.dto.request.PostEditRequest;
import com.example.board.post.dto.request.PostOneRequest;
import com.example.board.post.dto.request.PostRequest;
import com.example.board.post.dto.response.PostResponse;
import com.example.board.post.entity.Post;
import com.example.board.post.repository.PostRepository;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;
    private final BoardRepository boardRepository;

    // 게시글 저장
    public PostOneRequest savePost(String boardCategory, PostRequest postRequest, String nickname) {
        Board board = boardRepository.findById(boardCategory)
                .orElseThrow(()->new RuntimeException("그런 카테고리 없어요"));
        Post post = postRequest.toEntity(board,nickname);
        Post save = postRepository.save(post);
        return PostOneRequest.fromEntity(save);
    }

    // postId 로 게시글 찾기
    public PostOneRequest getPost(Long postId) {
//        Optional<Post> opt = postRepository.findByPostId(postId);
        Post post = postRepository.findById(postId)
                .orElseThrow(()-> new IllegalArgumentException("없는 게시물 입니다."));
        this.incrementViewCount(post);
        return PostOneRequest.fromEntity(post);
    }

    // 게시글 삭제
    @Transactional
    public void deletePost(Long postId) {
        Post post = postRepository.findByPostId(postId)
                .orElseThrow(() -> new RuntimeException("해당 게시물은 존재하지 않습니다."));
        postRepository.delete(post);
    }

    // 게시글 수정
    public Post updatePost(Long postId, PostEditRequest postEditRequest) {
        Post post = findByPostId(postId);
        post.update(postEditRequest.title(), postEditRequest.content());
        return postRepository.save(post);
    }

    // 조회수
    public void incrementViewCount(Post post) {
        post.setViewCount(post.getViewCount() + 1);
        postRepository.save(post);
    }

    // 신고
    // 누적 신고횟수 확인 하고 5가 되면 게시글 삭제
    @Transactional
    public void checkAndDeletePost(Long postId) {
        Post post = postRepository.findByPostId(postId)
                .orElseThrow(() -> new RuntimeException("해당 게시물은 존재하지 않습니다."));

        if (post.getPostComplainCount() >= 5) {
            postRepository.delete(post);
        }
    }
}
