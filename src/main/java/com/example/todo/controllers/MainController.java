package com.example.todo.controllers;

import com.example.todo.entities.Project;
import com.example.todo.entities.Task;
import com.example.todo.repositories.ProjectRepo;
import com.example.todo.repositories.TaskRepo;
import com.sun.org.apache.xalan.internal.xslt.Process;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class MainController {

    @Autowired
    private ProjectRepo projectRepo;

    @Autowired
    private TaskRepo taskRepo;

    @GetMapping("/")
    public String home(Model model){

        Iterable<Project> projects;
        projects = projectRepo.findAll();
        model.addAttribute("projects", projects);

        return "home";
    }
}
