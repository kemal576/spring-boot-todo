package com.sahinkemal.todoapp.Repository;

import com.sahinkemal.todoapp.Entity.Todo;
import org.springframework.data.repository.CrudRepository;

public interface TodoRepository extends CrudRepository<Todo,Long> {
}
