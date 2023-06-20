package com.codama.medium.todo.controller;

import com.codama.medium.todo.model.Todo;
import com.codama.medium.todo.model.TodoDTO;
import com.codama.medium.todo.service.TodoService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/todos")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
public class TodoController {

    private final TodoService todoService;

/**
 * This Java function returns a list of all Todo objects as a ResponseEntity.
 * 
 * @return A ResponseEntity object is being returned, which contains a list of all Todo objects and an
 * HTTP status code of 200 (OK) if the operation is successful. If an exception occurs, an error
 * response is returned.
 */
    @GetMapping("/")
    @ApiOperation(value="View a list of all Todo s", response = Todo.class, responseContainer = "List")
    public ResponseEntity<?> getAllTodos(){
        try {
            return new ResponseEntity<>(
                    todoService.getAllTodos(),
                    HttpStatus.OK);
        } catch (Exception e) {
            return errorResponse();
        }
    }

/**
 * This function creates a new Todo object and saves it using the TodoService class.
 * 
 * @param todoDTO todoDTO is an object of TodoDTO class which is used to transfer data between the
 * client and the server in the createTodo method. It is annotated with @RequestBody which means that
 * the data sent by the client in the request body will be automatically mapped to the fields of the
 * todoDTO object. This
 * @return A ResponseEntity object is being returned. It contains the saved Todo object and a HTTP
 * status code of 201 (CREATED) if the save operation was successful. If there was an exception during
 * the save operation, an error response is returned.
 */
    @PostMapping("/")
    @ApiOperation(value="Save new Todo ", response = Todo.class)
    public ResponseEntity<?> createTodo(@RequestBody TodoDTO todoDTO){
        try {
            return new ResponseEntity<>(
                    todoService.saveNewTodo(todoDTO),
                    HttpStatus.CREATED);
        } catch (Exception e) {
            return errorResponse();
        }
    }

/**
 * This function deletes a Todo with a specific ID and returns a response indicating whether the
 * deletion was successful or not.
 * 
 * @param id The id parameter is a Long value that represents the unique identifier of the Todo object
 * that needs to be deleted. It is passed as a path variable in the URL of the DELETE request.
 * @return This method returns a ResponseEntity object. The response entity contains an HTTP status
 * code and an optional response body. In this case, if the todo with the specified id is found and
 * deleted successfully, the response entity will have an HTTP status code of 200 (OK) and a response
 * body containing a message indicating that the todo was deleted. If the todo is not found, the
 * response entity will have an
 */
    @DeleteMapping("/{id}")
    @ApiOperation(value="Delete Todo  with specific id", response = String.class)
    public ResponseEntity<?> deleteTodo(@PathVariable Long id){
        try {
            Optional<Todo> optTodo = todoService.getTodoById(id);
            if (optTodo.isPresent()) {
                todoService.deleteTodo(optTodo.get());
                return new ResponseEntity<>(
                        String.format("Todo with id: %d was deleted", id),
                        HttpStatus.OK);
            } else {
                return noTodoFoundResponse(id);
            }
        } catch (Exception e) {
            return errorResponse();
        }
    }


    private ResponseEntity<String> errorResponse(){
        return new ResponseEntity<>("Something went wrong :(", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    private ResponseEntity<String> noTodoFoundResponse(Long id){
        return new ResponseEntity<>("No todo found with id: " + id, HttpStatus.NOT_FOUND);
    }
}
