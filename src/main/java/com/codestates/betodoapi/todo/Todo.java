package com.codestates.betodoapi.todo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;

@Getter
@Setter
@AllArgsConstructor
public class Todo {
    private final @Id Long id;
    private String title;
    @Column("TODO_ORDER")
    private Integer order;
    private Boolean completed;
}
