package com.sahinkemal.todoapp.Service.Concrete;

import com.sahinkemal.todoapp.Entity.ErrorResponse;
import com.sahinkemal.todoapp.Entity.LoginRequest;
import com.sahinkemal.todoapp.Entity.LoginResponse;
import com.sahinkemal.todoapp.Entity.User;
import com.sahinkemal.todoapp.Repository.UserRepository;
import com.sahinkemal.todoapp.Service.Abstract.AuthService;
import com.sahinkemal.todoapp.Util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtUtil jwtUtils;

    @Override
    public ResponseEntity<?> Login(LoginRequest loginRequest) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            loginRequest.getUsername(),
                            loginRequest.getPassword()
                    )
            );
        } catch (UsernameNotFoundException e) {
            return ResponseEntity.status(404)
                    .body(new ErrorResponse(404,false,"Username is not registered."));
        } catch (BadCredentialsException e) {
            return ResponseEntity.status(403)
                    .body(new ErrorResponse(403,false,"Password is wrong!"));
        }

        final String jwt = jwtUtils.generateToken(loginRequest.getUsername());
        final Long userId = userRepository.findByUsername(loginRequest.getUsername()).get().getId();
        return ResponseEntity.ok(new LoginResponse(userId, jwt));
    }

    @Override
    public ResponseEntity<?> Create(User user){
        if (userRepository.findByEmail(user.getEmail()).isPresent())
            return ResponseEntity.status(409).body(new ErrorResponse(409,false,"This e-mail address is already registered."));
        else if (userRepository.findByUsername(user.getUsername()).isPresent())
            return ResponseEntity.status(409).body(new ErrorResponse(409,false,"This username is already registered."));
        else
            return ResponseEntity.ok(userRepository.save(user));

    }
}
