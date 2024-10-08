package com.sparta.newsfeed14thfriday.domain.post.repository;

import com.sparta.newsfeed14thfriday.domain.post.entity.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


import java.time.LocalDateTime;
import java.util.List;

import java.util.Optional;
@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
    Optional<Post> findByPostId(Long postId);

    void deleteByPostId(Long postId);
    Page<Post> findByUser_EmailAndDeletedFalseOrderByUpdatedAtDesc(String Email, Pageable pageable);

    Page<Post> findByCreatedAtBetween(LocalDateTime startDateTime, LocalDateTime endDateTime, Pageable pageable);

    Page<Post> findAllByUser_EmailInAndDeletedFalseOrderByUpdatedAtDesc(List<String> email, Pageable pageable);





}
