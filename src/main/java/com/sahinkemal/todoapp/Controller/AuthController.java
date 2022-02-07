package com.sahinkemal.todoapp.Controller;

import com.sahinkemal.todoapp.Entity.LoginRequest;
import com.sahinkemal.todoapp.Entity.RegisterRequest;
import com.sahinkemal.todoapp.Service.Abstract.AuthService;
import io.swagger.v3.oas.annotations.Operation;
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
    @Operation(summary = "User login with credentials")
    public ResponseEntity<?> Login(@RequestBody LoginRequest loginRequest){
        return authService.Login(loginRequest);
    }

    @PostMapping("/register")
    @Operation(summary = "Create a new user")
    public ResponseEntity<?> Create(@RequestBody RegisterRequest register) {
        return authService.Create(register.toUser());
    }
}
