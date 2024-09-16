package br.com.supera.taskmanagement.test.controller;

import br.com.supera.taskmanagement.controller.TaskListController;
import br.com.supera.taskmanagement.model.TaskList;
import br.com.supera.taskmanagement.service.TaskListService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@WebMvcTest(TaskListController.class)
public class TaskListControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TaskListService taskListService;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    public void setUp() {
        // MockitoAnnotations.openMocks(this); // No need to manually open mocks here.
    }

    @Test
    public void testCreateTaskList() throws Exception {
        TaskList taskList = new TaskList();
        taskList.setId(1L);
        taskList.setName("Task List Name");

        when(taskListService.save(any(TaskList.class))).thenReturn(taskList);

        mockMvc.perform(MockMvcRequestBuilders.post("/task-lists")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(taskList)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("Task List Name"));
    }

    @Test
    public void testGetTaskList() throws Exception {
        TaskList taskList = new TaskList();
        taskList.setId(1L);
        taskList.setName("Task List Name");

        when(taskListService.findById(anyLong())).thenReturn(java.util.Optional.of(taskList));

        mockMvc.perform(MockMvcRequestBuilders.get("/task-lists/1"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("Task List Name"));
    }

    @Test
    public void testDeleteTaskList() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/task-lists/1"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
}
