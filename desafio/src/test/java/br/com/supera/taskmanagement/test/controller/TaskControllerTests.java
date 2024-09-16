package br.com.supera.taskmanagement.test.controller;

import br.com.supera.taskmanagement.controller.TaskController;
import br.com.supera.taskmanagement.model.Task;
import br.com.supera.taskmanagement.model.TaskStatus;
import br.com.supera.taskmanagement.service.TaskService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@WebMvcTest(TaskController.class)
public class TaskControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TaskService taskService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void testCreateTask() throws Exception {
        Task task = new Task();
        task.setId(1L);
        task.setTitle("Task Title");
        task.setStatus(TaskStatus.PENDING);
        task.setIsHighlighted(false);

        when(taskService.save(any(Task.class))).thenReturn(task);

        mockMvc.perform(MockMvcRequestBuilders.post("/tasks")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(task)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$.title").value("Task Title"));
    }

    @Test
    public void testGetTask() throws Exception {
        Task task = new Task();
        task.setId(1L);
        task.setTitle("Task Title");
        task.setStatus(TaskStatus.PENDING);
        task.setIsHighlighted(false);

        when(taskService.findById(anyLong())).thenReturn(java.util.Optional.of(task));

        mockMvc.perform(MockMvcRequestBuilders.get("/tasks/1"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$.title").value("Task Title"));
    }

    @Test
    public void testDeleteTask() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/tasks/1"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void testUpdateTaskStatus() throws Exception {
        Task task = new Task();
        task.setId(1L);
        task.setTitle("Task Title");
        task.setStatus(TaskStatus.PENDING);
        task.setIsHighlighted(false);

        when(taskService.updateStatus(anyLong(), any(TaskStatus.class))).thenReturn(task);

        mockMvc.perform(MockMvcRequestBuilders.patch("/tasks/1/status")
                        .param("newStatus", TaskStatus.COMPLETED.name()))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.status").value(TaskStatus.PENDING.name()));
    }

    @Test
    public void testUpdateTask() throws Exception {
        Task existingTask = new Task();
        existingTask.setId(1L);
        existingTask.setTitle("Old Task Title");
        existingTask.setStatus(TaskStatus.PENDING);
        existingTask.setIsHighlighted(false);

        Task updatedTask = new Task();
        updatedTask.setId(1L);
        updatedTask.setTitle("Updated Task Title");
        updatedTask.setStatus(TaskStatus.COMPLETED);
        updatedTask.setIsHighlighted(true);

        when(taskService.findById(anyLong())).thenReturn(Optional.of(existingTask));
        when(taskService.save(any(Task.class))).thenReturn(updatedTask);

        mockMvc.perform(MockMvcRequestBuilders.put("/tasks/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updatedTask)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.title").value("Updated Task Title"));
    }

}
