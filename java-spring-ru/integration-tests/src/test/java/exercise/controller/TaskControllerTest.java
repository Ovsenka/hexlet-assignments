package exercise.controller;

import org.junit.jupiter.api.Test;
import static net.javacrumbs.jsonunit.assertj.JsonAssertions.assertThatJson;
import static org.assertj.core.api.Assertions.assertThat;
import org.instancio.Instancio;
import org.instancio.Select;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;

import java.util.HashMap;
import com.fasterxml.jackson.databind.ObjectMapper;
import net.datafaker.Faker;
import exercise.repository.TaskRepository;
import exercise.model.Task;

// BEGIN
@AutoConfigureMockMvc
@SpringBootTest
// END
class ApplicationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private Faker faker;

    @Autowired
    private ObjectMapper om;

    @Autowired
    private TaskRepository taskRepository;


    @Test
    public void testWelcomePage() throws Exception {
        var result = mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andReturn();

        var body = result.getResponse().getContentAsString();
        assertThat(body).contains("Welcome to Spring!");
    }

    @Test
    public void testIndex() throws Exception {
        var result = mockMvc.perform(get("/tasks"))
                .andExpect(status().isOk())
                .andReturn();

        var body = result.getResponse().getContentAsString();
        assertThatJson(body).isArray();
    }


    // BEGIN
     @Test
    public void testShow() {
        Task task = getEntityInstance();

    }

    @Test
    public void testCreate() throws Exception {
        Task task = getEntityInstance();
        var request = post("/tasks")
                .contentType(MediaType.APPLICATION_JSON)
                .content(om.writeValueAsString(task));
        var result = mockMvc.perform(request)
                .andExpect(status().isCreated())
                .andDo(print())
                .andReturn();
        var body = result.getResponse().getContentAsString();
        Task savedTask = taskRepository.findByTitle(task.getTitle()).get();
        assertThat(savedTask).isNotNull();
        assertThatJson(body).and(
                a -> a.node("title").isEqualTo(task.getTitle()),
                a -> a.node("description").isEqualTo(task.getDescription()));

    }

    @Test
    public void testUpdate() throws Exception {
        Task task = getEntityInstance();
        var testRepoTask = taskRepository.save(task);

        var data = new HashMap<>();
        data.put("title", "Counter Strike 2");

        var request = put("/tasks/" + testRepoTask.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(om.writeValueAsString(data));

        var result = mockMvc.perform(request)
                .andExpect(status().isOk())
                .andReturn();

        var body = result.getResponse().getContentAsString();

        assertThatJson(body)
                .node("title").isEqualTo("Counter Strike 2");

        assertThat(taskRepository.findById(testRepoTask.getId()).get().getTitle()).isEqualTo(data.get("title"));

    }

    @Test
    public void testDestroy() throws Exception {
        Task task = taskRepository.save(getEntityInstance());
        var request = delete("/tasks/" + task.getId());
        mockMvc.perform(request)
                .andExpect(status().isOk());
        taskRepository.delete(task);
        assertThat(taskRepository.existsById(task.getId())).isFalse();
    }

    public Task getEntityInstance() {
        var task = Instancio.of(Task.class)
                .ignore(Select.field(Task::getId))
                .supply(Select.field(Task::getTitle), () -> faker.esports().game())
                .supply(Select.field(Task::getDescription), () -> faker.esports().event())
                .create();
        return task;
    }
    // END
}
