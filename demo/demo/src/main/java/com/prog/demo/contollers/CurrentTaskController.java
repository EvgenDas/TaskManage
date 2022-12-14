package com.prog.demo.contollers;

import com.prog.demo.models.Task;
import com.prog.demo.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.time.LocalDate;
import java.util.ArrayList;

import java.util.Objects;
import java.util.Optional;

import org.springframework.data.domain.Sort;


@Controller
public class CurrentTaskController {

    @Autowired
    private TaskRepository taskRepository;


    @GetMapping("/AllTask")
    public String AllTask(Model model) {
        Iterable<Task> tasks = taskRepository.findAll(Sort.by(Sort.Order.asc("priority")));
        model.addAttribute("tasks", tasks);
        return "AllTask";
    }

    @GetMapping("/AllTask/OpenTask")
    public String OpenTask(Model model) {
        LocalDate a = LocalDate.now();
        Date date = java.sql.Date.valueOf(a);
        Iterable<Task> tasks = taskRepository.findByStatus("Открыта");
        model.addAttribute("tasks", tasks);
        return "OpenTask";
    }

    @GetMapping("/AllTask/PerformedTask")
    public String PerformedTask(Model model) {
        LocalDate a = LocalDate.now();
        Date date = java.sql.Date.valueOf(a);
        Iterable<Task> tasks = taskRepository.findByStatus("Выполняется");
        model.addAttribute("tasks", tasks);
        return "PerformedTask";
    }

    @GetMapping("/AllTask/CanceledTask")
    public String CanceledTask(Model model) {
        LocalDate a = LocalDate.now();
        Date date = java.sql.Date.valueOf(a);
        Iterable<Task> tasks = taskRepository.findByStatus("Отменена");
        model.addAttribute("tasks", tasks);
        return "CanceledTask";
    }

    @GetMapping("/AllTask/CompleteTask")
    public String CompleteTask(Model model) {
        LocalDate a = LocalDate.now();
        Date date = java.sql.Date.valueOf(a);
        Iterable<Task> tasks = taskRepository.findByStatus("Завершена");
        model.addAttribute("tasks", tasks);
        return "CompleteTask";
    }


    @GetMapping("/AllTask/add")
    public String TaskAdd(Model model) {
        return "Task-add";


    }

    @PostMapping("/AllTask/add")
    public String TaskAddSmth(@RequestParam String title, @RequestParam String name, @RequestParam String priority, @RequestParam String description, @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date estimate_date, @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate today_date, Model model) {
        Task task = new Task(title, name, priority, "Открыта", description, estimate_date, today_date);
        taskRepository.save(task);
        return "redirect:/AllTask";
    }


    @GetMapping("/AllTask/{id}")
    public String TaskDetails(@PathVariable(value = "id") long id, Model model) {
        if (!taskRepository.existsById(id)) {
            return "redirect:/AllTask";
        }
        Optional<Task> task = taskRepository.findById(id);
        ArrayList<Task> result = new ArrayList<>();
        task.ifPresent(result::add);
        model.addAttribute("task", result);
        return "Task-details";

    }

    @GetMapping("/AllTask/{id}/edit")
    public String TaskEdit(@PathVariable(value = "id") long id, Model model) {
        if (!taskRepository.existsById(id)) {
            return "redirect:/AllTask";
        }
        Optional<Task> task = taskRepository.findById(id);
        ArrayList<Task> result = new ArrayList<>();
        task.ifPresent(result::add);
        model.addAttribute("task", result);
        return "Task-edit";

    }

    @PostMapping("/AllTask/{id}/edit")
    public String TaskUpdate(@PathVariable(value = "id") long id, @RequestParam String title, @RequestParam String name, @RequestParam String priority, @RequestParam String description, @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date estimate_date, Model model) {
        Task task = taskRepository.findById(id).orElseThrow();
        task.setTitle(title);
        task.setName(name);
        task.setPriority(priority);
        task.setDescription(description);
        task.setEstimate_date(estimate_date);
        taskRepository.save(task);

        return "redirect:/AllTask";
    }

    @PostMapping("/AllTask/{id}/performed")
    public String TaskUpdateStatusPerformed(@PathVariable(value = "id") long id, Model model) {
        Task task = taskRepository.findById(id).orElseThrow();
        if (Objects.equals(task.getStatus(), "Открыта")) {
            LocalDate t = LocalDate.now();
            task.setStatus("Выполняется");
            task.setToday_date(t);
            taskRepository.save(task);

        }
        return "redirect:/AllTask";
    }

    @PostMapping("/AllTask/{id}/canceled")
    public String TaskUpdateStatusCanceled(@PathVariable(value = "id") long id, Model model) {
        Task task = taskRepository.findById(id).orElseThrow();
        if (Objects.equals(task.getStatus(), "Открыта") || Objects.equals(task.getStatus(), "Выполняется")) {
            task.setStatus("Отменена");
            taskRepository.save(task);

        }

        return "redirect:/AllTask";
    }

    @PostMapping("/AllTask/{id}/complete")
    public String TaskUpdateStatusComplete(@PathVariable(value = "id") long id, Model model) {
        Task task = taskRepository.findById(id).orElseThrow();
        if (Objects.equals(task.getStatus(), "Выполняется")) {
            task.setActual_date(LocalDate.now());
            task.setStatus("Завершена");
            int day = task.getActual_date().getDayOfMonth() - task.getToday_date().getDayOfMonth();
            int month = task.getActual_date().getMonthValue() - task.getToday_date().getMonthValue();
            task.setElapsed_time("Дней: " + day + " Месяцев: " + month);
            taskRepository.save(task);
        }

        return "redirect:/AllTask/{id}";
    }

    @PostMapping("/AllTask/{id}/remove")
    public String TaskDelete(@PathVariable(value = "id") long id, Model model) {
        Task task = taskRepository.findById(id).orElseThrow();
        taskRepository.delete(task);
        return "redirect:/AllTask";
    }


}
