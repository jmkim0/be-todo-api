package com.codestates.betodoapi;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class TodoService {
    private final TodoRepository todoRepository;

    public Todo createTodo(Todo todo) {
        return todoRepository.save(todo);
    }

    public Todo readTodo(long id) {
        return todoRepository.findById(id).orElseThrow();
    }

    public List<Todo> readTodos() {
        return todoRepository.findAll();
    }

    public Todo updateTodo(Todo todo) {
        Todo foundTodo = todoRepository.findById(todo.getId()).orElseThrow();

        Optional.ofNullable(todo.getTitle()).ifPresent(foundTodo::setTitle);
        Optional.ofNullable(todo.getOrder()).ifPresent(foundTodo::setOrder);
        Optional.ofNullable(todo.getCompleted()).ifPresent(foundTodo::setCompleted);

        return todoRepository.save(foundTodo);
    }

    public void deleteTodo(long id) {
        todoRepository.delete(todoRepository.findById(id).orElseThrow());
    }

    public void deleteTodos() {
        todoRepository.deleteAll();
    }
}
