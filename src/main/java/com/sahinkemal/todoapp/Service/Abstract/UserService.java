package com.sahinkemal.todoapp.Service.Abstract;

import com.sahinkemal.todoapp.Entity.User;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface UserService {
    ResponseEntity<?> GetAll();
    ResponseEntity<?> GetById(Long id);
    ResponseEntity<?> Update(User user);
    ResponseEntity<?> Delete(Long id);
}
