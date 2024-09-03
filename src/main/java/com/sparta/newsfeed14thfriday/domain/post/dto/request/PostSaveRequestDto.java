package com.sparta.newsfeed14thfriday.domain.post.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PostSaveRequestDto {
    private Long userId;
    private String title;
    private String contents;
}
