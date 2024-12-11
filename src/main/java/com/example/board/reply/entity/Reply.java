package com.example.board.reply.entity;

import com.example.board.comment.entity.Comment;
import com.example.board.complain.entity.CommentComplain;
import com.example.board.complain.entity.ReplyComplain;
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
@Table(name = "reply")
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Reply {
    @Id
    @Column(name = "reply_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long replyId;

    @Column(name = "member_nickname")
    public String nickname;

    @Column(name = "member_id", nullable = false)
    private Long memberId;

    @Column(name = "reply_content", nullable = false)
    public String replyContent;

    @Column(name = "created_at")
    @CreationTimestamp
    public Date createdAt;

    @ManyToOne
    @JoinColumn(name = "comment_id", nullable = false)
    @JsonIgnore
    public Comment comment;

//    @ManyToOne
//    @JsonIgnore
//    public Post post;

    @OneToMany(mappedBy = "reply", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    @Builder.Default
    public List<ReplyComplain> replyComplains = new ArrayList<>();

    @Column(name = "reply_complain_count")
    @Builder.Default
    public Integer replyComplainCount = 0;

    public void setReplyComplainCount(Integer replyComplainCount) {
        this.replyComplainCount = replyComplainCount;
    }

    @PrePersist
    public void prePersist() {
        if (this.replyComplainCount == null) {
            this.replyComplainCount = 0;
        }
    }

    @Override
    public String toString() {
        return "Reply{" +
                "replyId=" + replyId +
                ", replyContent='" + replyContent + '\'' +
                ", createdAt=" + createdAt +
                '}';
    }
}
