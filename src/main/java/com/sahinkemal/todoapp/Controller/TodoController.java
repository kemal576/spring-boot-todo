package com.sahinkemal.todoapp.Controller;

import com.sahinkemal.todoapp.Entity.Todo;
import com.sahinkemal.todoapp.Entity.TodoCreate;
import com.sahinkemal.todoapp.Service.Abstract.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TodoController {
    @Autowired
    private TodoService todoService;

    @GetMapping("/todos")
    public List<Todo> GetAll() {
        return todoService.GetAll();
    }

    @GetMapping("todos/{id}")
    public ResponseEntity<?> GetById(@PathVariable Long id) {
        return todoService.GetById(id);
    }

    @GetMapping("/users/{id}/todos")
    public ResponseEntity<?> GetByUserId(@PathVariable Long id) {
        return todoService.GetTodosByUserId(id);
    }

    @PostMapping("/users/{id}/todos")
    public ResponseEntity<?> Create(@PathVariable Long id, @RequestBody TodoCreate todoCreate) {
        return todoService.Create(id, todoCreate.toTodo());
    }

    @PutMapping("/todos")
    public ResponseEntity<?> Update(@RequestBody Todo todo) {
        return todoService.Update(todo);
    }

    @DeleteMapping("/todos/{id}")
    public ResponseEntity<?> Delete(@PathVariable Long id) {
        return todoService.Delete(id);
    }

}
