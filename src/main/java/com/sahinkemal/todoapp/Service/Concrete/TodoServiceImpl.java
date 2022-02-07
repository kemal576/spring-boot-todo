package com.sahinkemal.todoapp.Service.Concrete;

import com.sahinkemal.todoapp.Entity.Todo;
import com.sahinkemal.todoapp.Entity.User;
import com.sahinkemal.todoapp.Repository.TodoRepository;
import com.sahinkemal.todoapp.Repository.UserRepository;
import com.sahinkemal.todoapp.Service.Abstract.TodoService;
import com.sahinkemal.todoapp.Util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class TodoServiceImpl implements TodoService {
    @Autowired
    private TodoRepository todoRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private JwtUtil jwtUtils;

    @Override
    public List<Todo> GetAll() {
        return todoRepository.findAll();
    }

    @Override
    public ResponseEntity<?> GetTodosByUserId(Long userId){
        if (jwtUtils.checkLoginUserWithUserId(userId)) {
            List<Todo> todos = todoRepository.findByUserId(userId);
            if (todos.isEmpty())
                return ResponseEntity.notFound().build();

            return ResponseEntity.ok(todos);
        }
        return ResponseEntity.status(403).build();
    }

    @Override
    public ResponseEntity<?> GetById(Long id) {
        Optional<Todo> todo = todoRepository.findById(id);
        if (todo.isPresent()){
            if (jwtUtils.checkLoginUserWithUserId(todo.get().getUser().getId()))
                return ResponseEntity.ok(todo);
            return ResponseEntity.status(403).build();
        }
        return ResponseEntity.notFound().build();
    }

    @Override
    public ResponseEntity<?> Create(Long userId, Todo todo){
        User user = userRepository.findById(userId).orElse(null);
        if (user != null){
            if (!jwtUtils.checkLoginUserWithUserId(user.getId()))
                return ResponseEntity.status(403).build();

            todo.setCreatedAt(new Date());
            todo.setUpdatedAt(new Date());
            todo.setUser(user);
            return ResponseEntity.status(201).body(todoRepository.save(todo));
        }
        return ResponseEntity.badRequest().build();
    }

    @Override
    public ResponseEntity<?> Update(Todo todo) {
        Optional<Todo> existingTodo = todoRepository.findById(todo.getId());
        if (existingTodo.isPresent()){
            if (jwtUtils.checkLoginUserWithUserId(existingTodo.get().getUser().getId())) {
                Todo newTodo = existingTodo.get();
                newTodo.setTitle(todo.getTitle());
                newTodo.setContent(todo.getContent());
                newTodo.setDone(todo.getDone());
                newTodo.setUpdatedAt(new Date());
                return ResponseEntity.ok(todoRepository.save(newTodo));
            }
            return ResponseEntity.status(403).build();
        }
        return ResponseEntity.notFound().build();
    }

    @Override
    public ResponseEntity<?> Delete(Long id) {
        Optional<Todo> existingTodo = todoRepository.findById(id);
        if (existingTodo.isPresent()) {
            if (jwtUtils.checkLoginUserWithUserId(id)) {
                todoRepository.deleteById(id);
                ResponseEntity.ok();
            }
            return ResponseEntity.status(403).build();
        }
        return ResponseEntity.notFound().build();
    }

    @Override
    public ResponseEntity<?> UpdateDone(Long id, Boolean doneStatus) {
        var todo = todoRepository.findById(id);
        if (todo.isPresent()){
            Todo updatedTodo = todo.get();
            if (jwtUtils.checkLoginUserWithUserId(todo.get().getUser().getId())) {
                updatedTodo.setDone(doneStatus);
                return ResponseEntity.ok(todoRepository.save(updatedTodo));
            }
            return ResponseEntity.status(403).build();
        }
        return ResponseEntity.notFound().build();
    }
}
