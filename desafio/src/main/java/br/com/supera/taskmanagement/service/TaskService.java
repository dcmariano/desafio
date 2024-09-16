package br.com.supera.taskmanagement.service;

import br.com.supera.taskmanagement.model.Task;
import br.com.supera.taskmanagement.model.TaskStatus;
import br.com.supera.taskmanagement.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;

    public Task save(Task task) {
        return taskRepository.save(task);
    }

    public Optional<Task> findById(Long id) {
        return taskRepository.findById(id);
    }

    public void deleteById(Long id) {
        taskRepository.deleteById(id);
    }

    public Task updateStatus(Long id, TaskStatus newStatus) {
        Optional<Task> optionalTask = taskRepository.findById(id);
        if (optionalTask.isPresent()) {
            Task task = optionalTask.get();
            task.setStatus(newStatus);
            return taskRepository.save(task);
        }
        return null;
    }
    public List<Task> findAllByTaskListOrdered(Long taskListId) {
        return taskRepository.findAllByTaskListOrdered(taskListId);
    }

    public List<Task> findAllByTaskListWithFilters(Long taskListId, TaskStatus status) {
        return taskRepository.findAllByTaskListWithFilters(taskListId, status);
    }

}


