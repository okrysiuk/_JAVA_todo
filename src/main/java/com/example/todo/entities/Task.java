package com.example.todo.entities;

import javax.persistence.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

@Entity
@Table(name = "tasks")
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private boolean status;
    private Calendar deadline;
    private int priority;

    public Task(){

    }
    public Task(String name) {
        super();
        this.name = name;
        this.status = true;
        this.deadline = new GregorianCalendar(2021, 0 , 1);
        this.priority = 2;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public Calendar getDeadline() {
        return deadline;
    }

    public void setDeadline(Calendar deadline) {
        this.deadline = deadline;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public String dateFormatter(Task task){

        String template = "dd/MMMM/yyyy";

        DateFormat df = new SimpleDateFormat(template);

        Date taskDate = task.getDeadline().getTime();

        String dateAsString = df.format(taskDate);

        return  dateAsString;

    }
}
