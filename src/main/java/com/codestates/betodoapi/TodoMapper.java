package com.codestates.betodoapi;

import com.codestates.betodoapi.dto.TodoPatchDto;
import com.codestates.betodoapi.dto.TodoPostDto;
import com.codestates.betodoapi.dto.TodoResponseDto;
import org.mapstruct.Mapper;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface TodoMapper {
    Todo todoPostDtoToTodo(TodoPostDto postDto);

    Todo todoPatchDtoToTodo(long id, TodoPatchDto patchDto);
    TodoResponseDto todoToTodoResponseDto(Todo todo, String url);
    default List<TodoResponseDto> todosToTodoResponseDtos(List<Todo> todos, String url) {
        return todos.stream()
                .map(todo -> todoToTodoResponseDto(todo, url + todo.getId()))
                .collect(Collectors.toList());
    }
}
