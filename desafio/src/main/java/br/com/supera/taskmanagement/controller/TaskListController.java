package br.com.supera.taskmanagement.controller;

import br.com.supera.taskmanagement.model.TaskList;
import br.com.supera.taskmanagement.service.TaskListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/task-lists")
public class TaskListController {

    @Autowired
    private TaskListService taskListService;

    @PostMapping
    public TaskList createTaskList(@RequestBody TaskList taskList) {
        return taskListService.save(taskList);
    }

    @GetMapping("/{id}")
    public Optional<TaskList> getTaskList(@PathVariable Long id) {
        return taskListService.findById(id);
    }

    @DeleteMapping("/{id}")
    public void deleteTaskList(@PathVariable Long id) {
        taskListService.deleteById(id);
    }
}
