package com.sahinkemal.todoapp.Service.Abstract;

import com.sahinkemal.todoapp.Entity.LoginRequest;
import com.sahinkemal.todoapp.Entity.User;
import org.springframework.http.ResponseEntity;


public interface AuthService {
    ResponseEntity<?> Login(LoginRequest loginRequest);
    ResponseEntity<?> Create(User user);
}
