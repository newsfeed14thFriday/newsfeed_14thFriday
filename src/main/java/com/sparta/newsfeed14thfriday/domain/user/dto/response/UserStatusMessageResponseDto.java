package com.sparta.newsfeed14thfriday.domain.user.dto.response;

import lombok.Getter;

@Getter
public class UserStatusMessageResponseDto {
    private final String updatedStatusMessage;

    public UserStatusMessageResponseDto(String message) {
        this.updatedStatusMessage = message;
    }
}
