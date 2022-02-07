package com.sahinkemal.todoapp.Service.Abstract;

import com.sahinkemal.todoapp.Entity.LoginRequest;
import org.springframework.http.ResponseEntity;


public interface AuthService {
    ResponseEntity<?> Login(LoginRequest loginRequest);
}
