package com.sahinkemal.todoapp.Controller;

import com.sahinkemal.todoapp.Entity.ErrorResponse;
import com.sahinkemal.todoapp.Entity.LoginRequest;
import com.sahinkemal.todoapp.Entity.LoginResponse;
import com.sahinkemal.todoapp.Service.Abstract.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<?> Login(@RequestBody LoginRequest loginRequest){
        return authService.Login(loginRequest);
    }
}
