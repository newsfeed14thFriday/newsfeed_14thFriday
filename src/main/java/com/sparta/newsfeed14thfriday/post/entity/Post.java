package com.sparta.newsfeed14thfriday.post.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor
public class Post extends Timestamped {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "post_id")
    private Long postId;

    @Column(length = 100, nullable = false)
    private String title;

    @Column(length = 500, nullable = false)
    private String contents;

    private Long commentCount;
    private Long postLikeCount;

    // 유저 아이디 FK
//    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
//    @JoinColumn(name = "id", nullable = false)
//    private User user;

    public static Post createNewPost(String title, String contents) {
        Post newPost = new Post();
        newPost.title = title;
        newPost.contents = contents;

        return newPost;
    }


    public void update(String title, String contents) {
        this.title = title;
        this.contents = contents;
    }
}
