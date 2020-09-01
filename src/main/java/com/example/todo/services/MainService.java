package com.example.todo.services;

import com.example.todo.entities.Project;
import com.example.todo.entities.Task;
import com.example.todo.repositories.ProjectRepo;
import com.example.todo.repositories.TaskRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class MainService {
    @Autowired
    private ProjectRepo projectRepo;

    @Autowired
    private TaskRepo taskRepo;

    public Iterable<Project> showAll(){
        Iterable<Project> projects;
        projects = projectRepo.findAll();
        return projects;
    }

    public boolean addProject(String name){

        Project projectFromDb = projectRepo.findByName(name);

        if (projectFromDb != null){
            return false;
        }
        Project project = new Project(name);
        this.projectRepo.save(project);

        return true;
    }
    public boolean deleteProject(long projectId){

        Project project = projectRepo.findById(projectId);

        List<Task> tasks = project.getTasks();

        for (Task task:tasks) {
            taskRepo.delete(task);
        }

        this.projectRepo.delete(project);

        return true;
    }

    public boolean addTask(String name, long projectId){

        Task taskFromDb = taskRepo.findByName(name);

        if (taskFromDb != null){
            return false;
        }
        Task task = new Task(name);

        Project project = projectRepo.findById(projectId);

        project.getTasks().add(task);

        this.projectRepo.save(project);

        return true;
    }
}
