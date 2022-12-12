package com.codestates.betodoapi.todo;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface TodoRepository extends CrudRepository<Todo, Long> {
    @Override
    List<Todo> findAll();
}
