package com.example.todo.controllers;


import com.example.todo.repositories.ProjectRepo;
import com.example.todo.repositories.TaskRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    @Autowired
    private TaskRepo taskRepo;

    @Autowired
    private ProjectRepo projectRepo;

    @GetMapping("/")
    public String home(){
        return "home";
    }
}
