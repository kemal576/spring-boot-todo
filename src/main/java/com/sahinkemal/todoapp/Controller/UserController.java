package com.sahinkemal.todoapp.Controller;

import com.sahinkemal.todoapp.Entity.RegisterRequest;
import com.sahinkemal.todoapp.Entity.User;
import com.sahinkemal.todoapp.Service.Abstract.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping
    public ResponseEntity<?> GetAll() {
        return userService.GetAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> GetById(@PathVariable Long id) {
        return userService.GetById(id);
    }

    @PostMapping("/register")
    public ResponseEntity<?> Create(@RequestBody RegisterRequest register) {
        return userService.Create(register.toUser());
    }

    @PutMapping
    public ResponseEntity<?> Update(@RequestBody User user) {
        return userService.Update(user);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> Delete(@PathVariable Long id) {
        return userService.Delete(id);
    }

}
