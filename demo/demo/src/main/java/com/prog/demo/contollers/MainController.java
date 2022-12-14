package com.prog.demo.contollers;

import com.prog.demo.models.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import com.prog.demo.repository.TaskRepository;

import java.time.LocalDate;
import java.util.Date;

@Controller
public class MainController {
    @Autowired
    private TaskRepository taskRepository;

    @GetMapping("/")
    public String home(Model model) {
        LocalDate a = LocalDate.now();
        Date date = java.sql.Date.valueOf(a);
        Iterable<Task> tasks = taskRepository.findByDate(date);
        model.addAttribute("tasks", tasks);
        return "home";
    }


}
