package com.example.todo.services;

import com.example.todo.entities.Project;
import com.example.todo.entities.Task;
import com.example.todo.repositories.ProjectRepo;
import com.example.todo.repositories.TaskRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
    public void deleteProject(long projectId){

        Project project = projectRepo.findById(projectId);

        List<Task> tasks = project.getTasks();

        for (Task task:tasks) {
            taskRepo.delete(task);
        }

        this.projectRepo.delete(project);

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

    public void deleteTask(long taskId) {

        Task task = taskRepo.findById(taskId);

        taskRepo.delete(task);
    }

    public Project getProject(long projectId){

        return projectRepo.findById(projectId);
    }

    public Task getTask(long taskId){

        return taskRepo.findById(taskId);
    }

    public boolean updateProject(Project project, String name){
        project.setName(name);
        projectRepo.save(project);
        return true;
    }

    public boolean updateTask(Task task, String name){
        task.setName(name);
        taskRepo.save(task);
        return true;
    }
    public void updateStatus(Task task, boolean doneTask){

        if (doneTask == true){
            task.setStatus(false);
        } else {
            task.setStatus(true);
        }
    }
}
