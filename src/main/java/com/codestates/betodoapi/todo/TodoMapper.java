package com.codestates.betodoapi.todo;

import com.codestates.betodoapi.todo.dto.TodoPatchDto;
import com.codestates.betodoapi.todo.dto.TodoPostDto;
import com.codestates.betodoapi.todo.dto.TodoResponseDto;
import org.mapstruct.Mapper;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface TodoMapper {
    Todo todoPostDtoToTodo(TodoPostDto postDto);

    Todo todoPatchDtoToTodo(Long id, TodoPatchDto patchDto);
    TodoResponseDto todoToTodoResponseDto(Todo todo, String url);
    default List<TodoResponseDto> todosToTodoResponseDtos(List<Todo> todos, String url) {
        return todos.stream()
                .map(todo -> todoToTodoResponseDto(todo, url + todo.getId()))
                .collect(Collectors.toList());
    }
}
