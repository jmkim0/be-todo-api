package com.codestates.betodoapi;

import com.codestates.betodoapi.dto.TodoPatchDto;
import com.codestates.betodoapi.dto.TodoPostDto;
import com.codestates.betodoapi.dto.TodoResponseDto;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Validated
@CrossOrigin
@RestController
public class TodoController {
    private final TodoService todoService;
    private final TodoMapper mapper;
    private final String rootUrl;

    public TodoController(TodoService todoService, TodoMapper mapper, @Value("${ROOT_URL}") String rootUrl) {
        this.todoService = todoService;
        this.mapper = mapper;
        this.rootUrl = rootUrl;
    }

    @PostMapping("/")
    public ResponseEntity<TodoResponseDto> postTodo(@Valid @RequestBody TodoPostDto postDto) {
        Todo todo = todoService.createTodo(mapper.todoPostDtoToTodo(postDto));

        return new ResponseEntity<>(
                mapper.todoToTodoResponseDto(todo, rootUrl + todo.getId()),
                HttpStatus.CREATED
        );
    }

    @GetMapping("/")
    public ResponseEntity<List<TodoResponseDto>> getTodos() {
        return new ResponseEntity<>(
                mapper.todosToTodoResponseDtos(todoService.readTodos(), rootUrl),
                HttpStatus.OK
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<TodoResponseDto> getTodo(@PathVariable @Positive long id) {
        return new ResponseEntity<>(
                mapper.todoToTodoResponseDto(todoService.readTodo(id), rootUrl + id),
                HttpStatus.OK
        );
    }

    @PatchMapping("/{id}")
    public ResponseEntity<TodoResponseDto> patchTodo(@PathVariable @Positive long id,
                                                     @Valid @RequestBody TodoPatchDto patchDto) {
        return new ResponseEntity<>(
                mapper.todoToTodoResponseDto(
                        todoService.updateTodo(mapper.todoPatchDtoToTodo(id, patchDto)),
                        rootUrl + id
                ),
                HttpStatus.OK
        );
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteTodo(@PathVariable @Positive long id) {
        todoService.deleteTodo(id);
    }

    @DeleteMapping("/")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteTodos() {
        todoService.deleteTodos();
    }
}
