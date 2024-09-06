package com.sparta.newsfeed14thfriday.domain.post.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class PostUpdateResponseDto {
    private String message;
    private Integer statusCode;
    private Long postId;
}
