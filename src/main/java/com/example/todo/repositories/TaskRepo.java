package com.example.todo.repositories;

import com.example.todo.entities.Task;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface TaskRepo extends CrudRepository<Task, Long> {
    List<Task> findAllByProject(String project);
}
