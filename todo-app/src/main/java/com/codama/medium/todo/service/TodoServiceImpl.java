package com.codama.medium.todo.service;

import com.codama.medium.todo.model.Todo;
import com.codama.medium.todo.model.TodoDTO;
import com.codama.medium.todo.repository.TodoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TodoServiceImpl implements TodoService {

    private final TodoRepository todoRepository;

/**
 * This function retrieves all Todo objects from the database and returns them as a List.
 * 
 * @return A list of all Todo objects from the todoRepository.
 */
    @Override
    @Transactional
    public List<Todo> getAllTodos() {
        List<Todo> todoList = new ArrayList<>();
        todoRepository.findAll().forEach(todoList::add);
        return todoList;
    }

/**
 * This Java function retrieves a Todo object by its ID from a repository using Spring's Transactional
 * annotation.
 * 
 * @param id The parameter "id" is a Long data type representing the unique identifier of a Todo
 * object. The method "getTodoById" retrieves a Todo object from the database by its id using the
 * Spring Data JPA method "findById". The method is annotated with "@Transactional" to ensure that the
 * database transaction
 * @return The method is returning an Optional object that contains a Todo entity with the specified
 * id. If a Todo entity with the specified id exists in the database, the Optional object will contain
 * the entity, otherwise it will be empty.
 */
    @Override
    @Transactional
    public Optional<Todo> getTodoById(Long id) {
        return todoRepository.findById(id);
    }

/**
 * This function saves a new Todo object by converting a TodoDTO object and using the TodoRepository to
 * save it.
 * 
 * @param todoDTO todoDTO is an object of TodoDTO class which contains the data for creating a new Todo
 * object. It may contain attributes such as title, description, due date, priority, etc.
 * @return The method `saveNewTodo` is returning an object of type `Todo`.
 */
    @Override
    @Transactional
    public Todo saveNewTodo(TodoDTO todoDTO) {
        return todoRepository.save(convertDTOToTodo(todoDTO));
    }

/**
 * This function deletes a Todo object from the database using the TodoRepository.
 * 
 * @param todo The parameter "todo" is an instance of the Todo class, which represents a single todo
 * item. This method is used to delete the given todo item from the database using the todoRepository.
 * The @Transactional annotation ensures that the operation is performed within a transaction, which
 * means that if any errors occur during
 */
    @Override
    @Transactional
    public void deleteTodo(Todo todo) {
        todoRepository.delete(todo);
    }

/**
 * The function converts a TodoDTO object to a Todo object by setting the title of the Todo object.
 * 
 * @param todoDTO todoDTO is an object of class TodoDTO which contains information about a todo item
 * such as its title, description, due date, etc.
 * @return An instance of the `Todo` class is being returned.
 */
    private Todo convertDTOToTodo(TodoDTO todoDTO){
        Todo todo = new Todo();
        todo.setTitle(todoDTO.getTitle());
        return todo;
    }

}
