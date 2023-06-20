package com.codama.medium.todo.service;

import com.codama.medium.todo.model.Todo;
import com.codama.medium.todo.repository.TodoRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class TodoServiceTest {

    TodoService todoService;
    @Mock
    TodoRepository todoRepository;

    @Before
    public void init() {
        todoService = new TodoServiceImpl(todoRepository);
    }

    @Test
    public void when2TodosInDatabase_thenGetListWithAllOfThem() {
        //given
        mockTodoInDatabase(2);

        //when
        List<Todo> todos = todoService.getAllTodos();

        //then
        assertEquals(2, todos.size());
    }

    private void mockTodoInDatabase(int todoCount) {
        when(todoRepository.findAll())
                .thenReturn(createTodoList(todoCount));
    }

    private List<Todo> createTodoList(int todoCount) {
        List<Todo> todos = new ArrayList<>();
        IntStream.range(0, todoCount)
                .forEach(number ->{
                    Todo todo = new Todo();
                    todo.setId(Long.valueOf(number));
                    todo.setTitle("Todo " + number);
                    todos.add(todo);
                });
        return todos;
    }
}
