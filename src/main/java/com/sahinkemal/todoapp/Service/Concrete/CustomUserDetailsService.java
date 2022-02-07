package com.sahinkemal.todoapp.Service.Concrete;

import com.sahinkemal.todoapp.Entity.User;
import com.sahinkemal.todoapp.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        var user = userRepository.findByUsername(username);
        if (user.isPresent()){
            return new org.springframework.security.core.userdetails
                    .User(user.get().getUsername(),user.get().getPassword(),new ArrayList<>());
        }
        else
            throw new UsernameNotFoundException("The given username is not registered.");

    }
}
