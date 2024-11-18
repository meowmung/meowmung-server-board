package com.example.board.comment.entity;

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

//    public Member commentAuthor;

    @Column(name = "created_at")
    @CreationTimestamp
    public Date createdAt;

    // 게시글과의 연관 관계
    // 외래키로 post_id 설정해주기
    @ManyToOne
    @JoinColumn(name = "post_id", nullable = false)
    public Post post;

    @OneToMany(mappedBy = "comment", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    public List<Reply> Replies = new ArrayList<>();

    @Override
    public String toString() {
        return "Comment{" +
                "commentId=" + commentId +
                ", commentContent='" + commentContent + '\'' +
                ", createdAt=" + createdAt +
                '}';
    }
}
