package com.prog.demo.models;
import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;

@Entity
@Table(name = "tasks")
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String title, description, elapsed_time, name, status;
    private String priority;
    @Temporal(TemporalType.DATE)
    private Date estimate_date;
    private LocalDate actual_date;
    private LocalDate today_date;






    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getEstimate_date() {
        return estimate_date;
    }

    public void setEstimate_date(Date Estimate_date) {
        this.estimate_date = Estimate_date;
    }

    public LocalDate getActual_date() {
        return actual_date;
    }

    public void setActual_date(LocalDate actual_date) {
        this.actual_date = actual_date;
    }

    public String getElapsed_time() {
        return elapsed_time;
    }

    public void setElapsed_time(String elapsed_time) {
        this.elapsed_time = elapsed_time;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    public LocalDate getToday_date() {
        return today_date;
    }

    public void setToday_date(LocalDate today_date) {
        this.today_date = today_date;
    }

    public Task() {
    }

    public Task(String title, String name, String priority, String status, String description, Date Estimate_date) {
        this.title = title;
        this.description = description;
        this.estimate_date = Estimate_date;
        this.priority = priority;
        this.name = name;
        this.status = status;
    }
    public Task(String title, String name, String priority, String status, String description, Date Estimate_date, LocalDate today_date) {
        this.title = title;
        this.description = description;
        this.estimate_date = Estimate_date;
        this.priority = priority;
        this.name = name;
        this.status = status;
        this.today_date = today_date;
    }

}
