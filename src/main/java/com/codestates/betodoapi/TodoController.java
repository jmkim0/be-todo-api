package com.codestates.betodoapi;

import com.codestates.betodoapi.dto.TodoPatchDto;
import com.codestates.betodoapi.dto.TodoPostDto;
import com.codestates.betodoapi.dto.TodoResponseDto;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;

@Validated
@CrossOrigin
@RestController
@AllArgsConstructor
public class TodoController {
    private final TodoService todoService;
    private final TodoMapper mapper;

    @PostMapping("/")
    public ResponseEntity<TodoResponseDto> postTodo(@Valid @RequestBody TodoPostDto postDto) {
        Todo todo = todoService.createTodo(mapper.todoPostDtoToTodo(postDto));

        return new ResponseEntity<>(
                mapper.todoToTodoResponseDto(
                        todo,
                        ServletUriComponentsBuilder.fromCurrentRequest()
                                .scheme("https").path(todo.getId().toString()).toUriString()
                ),
                HttpStatus.CREATED
        );
    }

    @GetMapping("/")
    public ResponseEntity<List<TodoResponseDto>> getTodos() {
        return new ResponseEntity<>(
                mapper.todosToTodoResponseDtos(
                        todoService.readTodos(),
                        ServletUriComponentsBuilder.fromCurrentRequest().scheme("https").toUriString()
                ),
                HttpStatus.OK
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<TodoResponseDto> getTodo(@PathVariable @Positive Long id) {
        return new ResponseEntity<>(
                mapper.todoToTodoResponseDto(
                        todoService.readTodo(id),
                        ServletUriComponentsBuilder.fromCurrentRequest().scheme("https").toUriString()
                ),
                HttpStatus.OK
        );
    }

    @PatchMapping("/{id}")
    public ResponseEntity<TodoResponseDto> patchTodo(@PathVariable @Positive Long id,
                                                     @Valid @RequestBody TodoPatchDto patchDto) {
        return new ResponseEntity<>(
                mapper.todoToTodoResponseDto(
                        todoService.updateTodo(mapper.todoPatchDtoToTodo(id, patchDto)),
                        ServletUriComponentsBuilder.fromCurrentRequest().scheme("https").toUriString()
                ),
                HttpStatus.OK
        );
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteTodo(@PathVariable @Positive Long id) {
        todoService.deleteTodo(id);
    }

    @DeleteMapping("/")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteTodos() {
        todoService.deleteTodos();
    }
}
