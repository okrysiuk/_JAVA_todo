package com.example.todo.controllers;

import com.example.todo.services.MainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MainController {

    @Autowired
    private MainService mainService;

    @GetMapping("/")
    public String home(Model model){

        model.addAttribute("projects", mainService.showAll());

        return "home";
    }

    @PostMapping("/addProject")
    public String addProject(@RequestParam(required = false) String name, Model model){

        if(!mainService.addProject(name)){
            model.addAttribute("projectExists", "Same project already exits");
        }

        return "redirect:/";
    }
    @PostMapping("/delete/{id}")
    public String deleteProject(@PathVariable(value = "id")long projectId){

        mainService.deleteProject(projectId);

        return "redirect:/";
    }
    @PostMapping("/addTask/{id}")
    public String addTask(@PathVariable(value = "id")long projectId,
                          @RequestParam(required = false) String name,
                          Model model){

        if(!mainService.addTask(name, projectId)){
            model.addAttribute("taskExists", "Same task already exits");
        }

        return "redirect:/";
    }
}
