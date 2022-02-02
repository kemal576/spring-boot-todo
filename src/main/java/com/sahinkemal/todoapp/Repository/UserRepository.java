package com.sahinkemal.todoapp.Repository;

import com.sahinkemal.todoapp.Entity.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User,Long> {
}
