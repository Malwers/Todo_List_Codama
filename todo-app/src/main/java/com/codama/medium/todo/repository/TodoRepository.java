package com.codama.medium.todo.repository;

import com.codama.medium.todo.model.Todo;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TodoRepository extends CrudRepository<Todo, Long> {

    Optional<Todo> findByTitle(String title);
}
