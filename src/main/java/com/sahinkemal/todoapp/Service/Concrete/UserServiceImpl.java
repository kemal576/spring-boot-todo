package com.sahinkemal.todoapp.Service.Concrete;

import com.sahinkemal.todoapp.Entity.ErrorResponse;
import com.sahinkemal.todoapp.Entity.User;
import com.sahinkemal.todoapp.Repository.UserRepository;
import com.sahinkemal.todoapp.Service.Abstract.UserService;
import com.sahinkemal.todoapp.Util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtUtil jwtUtils;

    @Override
    public ResponseEntity<?> GetAll() {
        List<User> users = userRepository.findAll();
        if (users.isEmpty())
            return ResponseEntity.status(404)
                    .body(new ErrorResponse(404,false,"No users found."));
        else
            return ResponseEntity.ok(users);
    }

    @Override
    public ResponseEntity<?> GetById(Long id) {
        if (jwtUtils.checkLoginUserWithUserId(id)){
            var user = userRepository.findById(id);
            if (user.isPresent()){
                return ResponseEntity.ok(user);
            }
            return ResponseEntity.status(404)
                    .body(new ErrorResponse(404,false,"User not found."));
        }
        return ResponseEntity.status(403)
                .body(new ErrorResponse(403,false,"You are not authorized for this action."));
    }

    @Override
    public ResponseEntity<?> Update(User user) {
        if (jwtUtils.checkLoginUserWithUserId(user.getId())) {
            User existingUser = userRepository.findById(user.getId()).orElse(null);
            if (existingUser != null){
                existingUser.setUsername(user.getUsername());
                existingUser.setEmail(user.getEmail());
                existingUser.setPassword(user.getPassword());
                return ResponseEntity.ok(userRepository.save(existingUser));
            }
        }
        return ResponseEntity.status(403).body(new ErrorResponse(403,false,"You are not authorized for this action."));
    }

    @Override
    public ResponseEntity<?> Delete(Long id) {
        if (jwtUtils.checkLoginUserWithUserId(id)){
            userRepository.deleteById(id);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.status(403).body(new ErrorResponse(403,false,"You are not authorized for this action."));
    }
}
