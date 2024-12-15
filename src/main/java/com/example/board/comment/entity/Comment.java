package com.example.board.comment.entity;

import com.example.board.complain.entity.CommentComplain;
import com.example.board.reply.entity.Reply;
import com.example.board.post.entity.Post;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

@Entity
@Table(name = "comment")
@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Comment {
    @Id
    @Column(name = "comment_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long commentId;

    @Column(name = "comment_content", nullable = false)
    public String commentContent;

    @Column(name = "member_nickname")
    public String nickname;

    @Column(name = "member_id", nullable = false)
    private Long memberId;

    @Column(name = "created_at")
    @CreationTimestamp
    public Date createdAt;

    @Column(name = "comment_complain_count")
    @Builder.Default
    public Integer commentComplainCount = 0;

    @OneToMany(mappedBy = "comment", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    @Builder.Default
    public List<CommentComplain> commentComplains = new ArrayList<>();

    // 게시글과의 연관 관계
    // 외래키로 post_id 설정해주기
    @ManyToOne
    @JoinColumn(name = "post_id", nullable = false)
    @JsonIgnore
    public Post post;

    @OneToMany(mappedBy = "comment", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @Builder.Default
    public List<Reply> replies = new ArrayList<>();

    @PrePersist
    public void prePersist() {
        if (this.commentComplainCount == null) {
            this.commentComplainCount = 0;
        }
    }

    public void setCommentComplainCount(Integer commentComplainCount) {
        this.commentComplainCount = commentComplainCount;
    }

    @Override
    public String toString() {
        return "Comment{" +
                "commentId=" + commentId +
                ", commentContent='" + commentContent + '\'' +
                ", createdAt=" + createdAt +
                '}';
    }
}
