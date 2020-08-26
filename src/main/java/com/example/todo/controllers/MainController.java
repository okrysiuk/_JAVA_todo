package com.example.todo.controllers;


import com.example.todo.entities.Project;
import com.example.todo.entities.Task;
import com.example.todo.repositories.ProjectRepo;
import com.example.todo.repositories.TaskRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    @Autowired
    private TaskRepo taskRepo;

    @Autowired
    private ProjectRepo projectRepo;

    @GetMapping("/")
    public String home(Model model){
        Iterable<Project> projects;
        Iterable<Task> tasks;
        projects =  projectRepo.findAll();
        tasks = taskRepo.findAll();
        model.addAttribute("projects", projects);
        model.addAttribute("tasks", tasks);
        return "home";
    }
}
