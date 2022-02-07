package com.sahinkemal.todoapp.Service.Abstract;

import com.sahinkemal.todoapp.Entity.Todo;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface TodoService {
    List<Todo> GetAll();
    ResponseEntity<?> GetTodosByUserId(Long userId);
    ResponseEntity<?> GetById(Long id);
    ResponseEntity<?> Create(Long userId, Todo todo);
    ResponseEntity<?> Update(Todo todo);
    ResponseEntity<?> Delete(Long id);
    ResponseEntity<?> UpdateDone(Long id, Boolean done);

}
