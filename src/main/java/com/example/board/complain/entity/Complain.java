package com.example.board.complain.entity;

import com.example.board.post.entity.Post;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

@Entity
@Table(name = "complain")
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Complain {
    @Id
    @Column(name = "complain_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long complainId;

//    public Member reporter;

    @Column(name = "complain_content", nullable = false)
    public String complainContent;

    @Column(name = "complain_count")
    public Integer complainCount = 0;

    @Column(name = "created_at")
    @CreationTimestamp
    public Date createdAt;

    @ManyToOne
    @JoinColumn(name = "post_id", nullable = false)
    public Post post;

    @PrePersist
    public void prePersist() {
        if (this.complainCount == null) {
            this.complainCount = 0;
        }
    }

    public void manageComplainCount(Integer complainCount) {
        this.complainCount += complainCount;
    }

    public void setComplainCount(Integer complainCount) {
        this.complainCount = complainCount;
    }

}
