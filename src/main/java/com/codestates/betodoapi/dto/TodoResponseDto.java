package com.codestates.betodoapi.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class TodoResponseDto {
    private final Long id;
    private final String title;

    private final int order;
    private final boolean completed;
    private final String url;
}
