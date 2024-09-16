package br.com.supera.taskmanagement.repository;

import br.com.supera.taskmanagement.model.TaskList;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskListRepository extends JpaRepository<TaskList, Long> {
}