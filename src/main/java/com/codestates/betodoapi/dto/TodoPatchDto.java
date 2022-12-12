package com.codestates.betodoapi.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import jakarta.validation.constraints.Size;
import lombok.Getter;

import java.util.Map;

@Getter
public class TodoPatchDto {
    @Size(min = 1)
    private final String title;
    private final Integer order;
    private final Boolean completed;

    @JsonCreator
    public TodoPatchDto(Map<String, Object> delegate) {
        title = (String) delegate.get("title");
        order = (Integer) delegate.get("order");
        completed = (Boolean) delegate.get("completed");
    }
}
