package com.example.todo.repositories;

import com.example.todo.entities.Project;
import org.springframework.data.repository.CrudRepository;

public interface ProjectRepo extends CrudRepository<Project, Long> {
}
