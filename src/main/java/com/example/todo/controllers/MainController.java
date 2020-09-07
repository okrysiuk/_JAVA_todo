package com.example.todo.controllers;

import com.example.todo.entities.Project;
import com.example.todo.entities.Task;
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

    @PostMapping("/add-project")
    public String addProject(@RequestParam(required = false) String name, Model model){

        if(!mainService.addProject(name)){
            model.addAttribute("projectExists", "Same project already exits");
        }

        return "redirect:/";
    }
    @PostMapping("/delete-project/{id}")
    public String deleteProject(@PathVariable(value = "id")long projectId){

        mainService.deleteProject(projectId);

        return "redirect:/";
    }
    @PostMapping("/add-task/{id}")
    public String addTask(@PathVariable(value = "id")long projectId,
                          @RequestParam(required = false) String name,
                          Model model){

        if(!mainService.addTask(name, projectId)){
            model.addAttribute("taskExists", "Same task already exits");
        }

        return "redirect:/";
    }
    @PostMapping("/delete-task/{id}")
    public String deleteTask(@PathVariable(value = "id")long taskId){

        mainService.deleteTask(taskId);

        return "redirect:/";
    }
    @GetMapping("/edit-project/{id}")
    public String editProject(@PathVariable(value = "id")long projectId, Model model){

        Project project = mainService.getProject(projectId);

        model.addAttribute("project", project);

        return "edit-project";
    }
    @GetMapping("/edit-task/{id}")
    public String editTask(@PathVariable(value = "id")long taskId, Model model){

        Task task = mainService.getTask(taskId);

        model.addAttribute("task", task);

        return "edit-task";
    }
}
