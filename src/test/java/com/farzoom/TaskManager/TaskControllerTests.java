package com.farzoom.TaskManager;

import com.farzoom.TaskManager.model.Task;
import com.google.gson.Gson;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
class TaskControllerTests {
	@Autowired
	private MockMvc mockMvc;

	private static final String URI = "http://localhost:" + "8080" + "/tasks";

	@Test
	public void getTaskTest() throws Exception {
		final String RESPONSE_LINE = "ololo push push";
		mockMvc.perform(get(URI + "/1")).
				andDo(print()).
				andExpect(MockMvcResultMatchers.status().isOk()).
				andExpect(MockMvcResultMatchers.content().string(RESPONSE_LINE));
	}

	@Test
	public void createTask() throws Exception {
		Task taskForCreate = new Task(1501L, "task999", "description");

		mockMvc.perform(post(URI + "/create").
					contentType(MediaType.APPLICATION_JSON).
					content(new Gson().toJson(taskForCreate))).
				andDo(print()).
				andExpect(MockMvcResultMatchers.status().isOk()).
				andExpect(MockMvcResultMatchers.content().json(new Gson().toJson(taskForCreate)));
	}

	@Test
	public void updateTaskTest() throws Exception {
		Long updateTaskId = 30l;
		String taskNameFromBody = "new_task";
		String taskDescriptionFromBody = "description";
		Task taskForUpdate = new Task(null, taskNameFromBody, taskDescriptionFromBody);

		Task returnedTask = new Task(updateTaskId, taskNameFromBody, taskDescriptionFromBody);

		mockMvc.perform(put(URI + "/update/" + updateTaskId).
					contentType(MediaType.APPLICATION_JSON).
					content(new Gson().toJson(taskForUpdate))).
				andDo(print()).
				andExpect(MockMvcResultMatchers.status().isOk()).
				andExpect(MockMvcResultMatchers.content().json(new Gson().toJson(returnedTask)));
	}
}
