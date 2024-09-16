package br.com.supera.taskmanagement.service;

import br.com.supera.taskmanagement.model.TaskList;
import br.com.supera.taskmanagement.repository.TaskListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TaskListService {

    @Autowired
    private TaskListRepository taskListRepository;

    public TaskList save(TaskList taskList) {
        return taskListRepository.save(taskList);
    }

    public Optional<TaskList> findById(Long id) {
        return taskListRepository.findById(id);
    }

    public void deleteById(Long id) {
        taskListRepository.deleteById(id);
    }
}
