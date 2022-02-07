package com.sahinkemal.todoapp.Controller;

import com.sahinkemal.todoapp.Entity.Todo;
import com.sahinkemal.todoapp.Entity.TodoCreate;
import com.sahinkemal.todoapp.Service.Abstract.TodoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@SecurityRequirement(name = "security_scheme")
public class TodoController {
    @Autowired
    private TodoService todoService;

    @GetMapping("/todos")
    @Operation(summary = "Get all todos from DB")
    public List<Todo> GetAll() {
        return todoService.GetAll();
    }

    @GetMapping("todos/{id}")
    @Operation(summary = "Get specified todo by id")
    public ResponseEntity<?> GetById(@PathVariable Long id) {
        return todoService.GetById(id);
    }

    @GetMapping("/users/{id}/todos")
    @Operation(summary = "Get specified user's todos by user id")
    public ResponseEntity<?> GetByUserId(@PathVariable Long id) {
        return todoService.GetTodosByUserId(id);
    }

    @PostMapping("/users/{id}/todos")
    @Operation(summary = "Create a new todo for a user")
    public ResponseEntity<?> Create(@PathVariable Long id, @RequestBody TodoCreate todoCreate) {
        return todoService.Create(id, todoCreate.toTodo());
    }

    @PutMapping("/todos")
    @Operation(summary = "Update a todo")
    public ResponseEntity<?> Update(@RequestBody Todo todo) {
        return todoService.Update(todo);
    }

    @DeleteMapping("/todos/{id}")
    @Operation(summary = "Delete a todo from DB by id")
    public ResponseEntity<?> Delete(@PathVariable Long id) {
        return todoService.Delete(id);
    }

}
