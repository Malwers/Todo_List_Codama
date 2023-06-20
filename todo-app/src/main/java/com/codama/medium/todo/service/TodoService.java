package com.codama.medium.todo.service;

import com.codama.medium.todo.model.Todo;
import com.codama.medium.todo.model.TodoDTO;

import java.util.List;
import java.util.Optional;

public interface TodoService {

    List<Todo> getAllTodos();

    Optional<Todo> getTodoById(Long id);

    Todo saveNewTodo(TodoDTO todoDTO);

    void deleteTodo(Todo todo);
}
