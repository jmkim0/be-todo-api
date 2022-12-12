package com.codestates.betodoapi.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Getter;

import java.util.Map;
import java.util.Optional;

@Getter
public class TodoPostDto {
    @NotNull
    @Size(min = 1)
    private final String title;
    @Positive
    private final int order;
    private final boolean completed;

    @JsonCreator
    public TodoPostDto(Map<String, Object> delegate) {
        title = (String) delegate.get("title");
        order = Optional.ofNullable((Integer) delegate.get("order")).orElse(1);
        completed = Optional.ofNullable((Boolean) delegate.get("completed")).orElse(false);
    }
}
