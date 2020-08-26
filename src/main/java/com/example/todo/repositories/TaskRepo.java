package com.example.todo.repositories;

import com.example.todo.entities.Task;
import org.springframework.data.repository.CrudRepository;

public interface TaskRepo extends CrudRepository<Task, Long> {
}
