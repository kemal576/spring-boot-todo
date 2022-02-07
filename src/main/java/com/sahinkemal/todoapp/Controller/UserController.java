package com.sahinkemal.todoapp.Controller;

import com.sahinkemal.todoapp.Entity.User;
import com.sahinkemal.todoapp.Service.Abstract.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@SecurityRequirement(name = "security_scheme")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping
    @Operation(summary = "Get all users from DB")
    public ResponseEntity<?> GetAll() {
        return userService.GetAll();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get specified user by id")
    public ResponseEntity<?> GetById(@PathVariable Long id) {
        return userService.GetById(id);
    }

    @PutMapping
    @Operation(summary = "Update a user")
    public ResponseEntity<?> Update(@RequestBody User user) {
        return userService.Update(user);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a user from DB by id")
    public ResponseEntity<?> Delete(@PathVariable Long id) {
        return userService.Delete(id);
    }

}
