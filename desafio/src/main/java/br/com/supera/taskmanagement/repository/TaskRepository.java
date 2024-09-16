package br.com.supera.taskmanagement.repository;

import br.com.supera.taskmanagement.model.Task;
import br.com.supera.taskmanagement.model.TaskStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Long> {

    @Query("SELECT t FROM Task t WHERE t.taskList.id = :taskListId ORDER BY t.isHighlighted DESC, t.title ASC")
    List<Task> findAllByTaskListOrdered(@Param("taskListId") Long taskListId);

    @Query("SELECT t FROM Task t WHERE t.taskList.id = :taskListId AND (:status IS NULL OR t.status = :status) ORDER BY t.isHighlighted DESC, t.title ASC")
    List<Task> findAllByTaskListWithFilters(@Param("taskListId") Long taskListId, @Param("status") TaskStatus status);
}