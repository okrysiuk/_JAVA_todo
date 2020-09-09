package com.example.todo.services;

import com.example.todo.entities.Project;
import com.example.todo.entities.Task;
import com.example.todo.repositories.ProjectRepo;
import com.example.todo.repositories.TaskRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.GregorianCalendar;
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

    public void updateTask(Task task, String name){
        task.setName(name);
        taskRepo.save(task);
    }
    public void updateStatus(Task task, boolean doneTask){

        task.setStatus(!doneTask);
    }
    public void updateDate(Task task, int year, int month, int day) {

        Calendar defaultDeadline = new GregorianCalendar(2021, Calendar.JANUARY, 1);

        Calendar newDeadline = new GregorianCalendar(year, month, day);
        if(!defaultDeadline.equals(newDeadline)){
            try {
                task.setDeadline(newDeadline);
            } catch (Exception e) {
                System.out.println("Wrong date!");
            }
        }
        taskRepo.save(task);
    }
        public void updatePriority (Task task, int priority){
            if(priority != 0)
            task.setPriority(priority);

            taskRepo.save(task);
        }
}
