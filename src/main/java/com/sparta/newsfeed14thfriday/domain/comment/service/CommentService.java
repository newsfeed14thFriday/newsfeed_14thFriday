package com.sparta.newsfeed14thfriday.domain.comment.service;


import com.sparta.newsfeed14thfriday.domain.comment.dto.request.CommentSaveRequestDto;
import com.sparta.newsfeed14thfriday.domain.comment.dto.request.CommentUpdateRequestDto;
import com.sparta.newsfeed14thfriday.domain.comment.dto.response.CommentDetailResponseDto;
import com.sparta.newsfeed14thfriday.domain.comment.dto.response.CommentSaveResponseDto;
import com.sparta.newsfeed14thfriday.domain.comment.dto.response.CommentUpdateResponseDto;
import com.sparta.newsfeed14thfriday.domain.comment.entity.Comment;
import com.sparta.newsfeed14thfriday.domain.comment.repository.CommentRepository;
import com.sparta.newsfeed14thfriday.domain.post.entity.Post;
import com.sparta.newsfeed14thfriday.domain.post.repository.PostRepository;
import com.sparta.newsfeed14thfriday.domain.user.entity.User;
import com.sparta.newsfeed14thfriday.domain.user.repository.UserRepository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.ArrayList;
import java.util.List;

@Service
public class CommentService {

    private final CommentRepository commentRepository;
    private final PostRepository postRepository;
    private final UserRepository userRepository;

    public CommentService(CommentRepository commentRepository , PostRepository postRepository, UserRepository userRepository) {
        this.commentRepository = commentRepository;
        this.postRepository = postRepository;
        this.userRepository = userRepository;
    }


    // service 에서 request , response 직접 다루지 말 것
    public CommentSaveResponseDto createComment(Long postId, CommentSaveRequestDto commentSaveRequestDto) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new NullPointerException("Post not found"));

        User email = userRepository.findByEmail(commentSaveRequestDto.getEmail())
                .orElseThrow(() -> new NullPointerException("User not found"));

        Comment comment = new Comment(
                commentSaveRequestDto.getContents(),
                email,
                post
        );

        Comment savedComment = commentRepository.save(comment);

        return new CommentSaveResponseDto(
                savedComment.getCommentId(),
                email,
                savedComment.getContents(),
                savedComment.getCommentLikeCount(),
                savedComment.getCreatedAt(),
                savedComment.getUpdatedAt()
                );

    }


    // 포스트의 코멘트 전부 확인
    public List<CommentDetailResponseDto> getComments(Long postId) {

        List<Comment> commentList = commentRepository.findByPostIdWithUser(postId);

        List<CommentDetailResponseDto> dtoList = new ArrayList<>();
        for (Comment comment : commentList) {
            CommentDetailResponseDto dto = new CommentDetailResponseDto(
                    comment.getCommentId(),
                    comment.getEmail(),
                    comment.getContents(),
                    comment.getCommentLikeCount(),
                    comment.getCreatedAt(),
                    comment.getUpdatedAt()
            );
            dtoList.add(dto);
        }

        return dtoList;

    }


    @Transactional
    public CommentUpdateResponseDto updateComment(Long commentId, CommentUpdateRequestDto commentUpdateRequestDto){
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new NullPointerException("Comment not found"));

        User email = userRepository.findByEmail(commentUpdateRequestDto.getEmail())
                .orElseThrow(()-> new NullPointerException("User not found"));

        // 가독성을 위해서 이름 바꾸는거 생각
        if ((comment.getEmail() == null) || !ObjectUtils.nullSafeEquals(email.getEmail(), comment.getEmail().getEmail())){
            throw new IllegalArgumentException("Email not match");
        }

        comment.update(commentUpdateRequestDto.getContents());

        return new CommentUpdateResponseDto(
                comment.getCommentId(),
                email,
                comment.getContents(),
                comment.getCommentLikeCount(),
                comment.getCreatedAt(),
                comment.getUpdatedAt()
        );
    }


    @Transactional
    public void deleteComment(Long commentId , CommentUpdateRequestDto commentUpdateRequestDto) {
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new NullPointerException("Comment not found"));

        User email = userRepository.findByEmail(commentUpdateRequestDto.getEmail())
                .orElseThrow(()-> new NullPointerException("User not found"));

        // 가독성을 위해서 이름 바꾸는거 생각
        if ((comment.getEmail() == null) || !ObjectUtils.nullSafeEquals(email.getEmail(), comment.getEmail().getEmail())){
            throw new IllegalArgumentException("Email not match");
        }



    }






}
