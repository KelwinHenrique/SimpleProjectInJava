package com.example.crudJwtTests.integrations;

import com.example.crudJwtTests.domain.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest()
@AutoConfigureMockMvc
public class UserIntegrationTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void createUserWithSuccess() throws Exception {
        User user = new User("Niwlek Niwlek", "Niwlek@galaxy.net", "NiwlekNiwlek");
        mockMvc.perform(post("/users")
                .contentType("application/json")
                .param("sendWelcomeMail", "true")
                .content(objectMapper.writeValueAsString(user)))
                .andExpect(status().isCreated());
    }

    @Test
    void createUserNameIsRequired() throws Exception {
        User user = new User(null, "Niwlek@galaxy.net", "NiwlekNiwlek");
        mockMvc.perform(post("/users")
                .contentType("application/json")
                .param("sendWelcomeMail", "true")
                .content(objectMapper.writeValueAsString(user)))
                .andExpect(status().isBadRequest())
                .andExpect(MockMvcResultMatchers.jsonPath("$.msg").value("Validation Error"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.errors[0].message").value("Name is required!"));
    }

    @Test
    void createUserEmailIsRequired() throws Exception {
        User user = new User("Niwlek Niwlek", null, "NiwlekNiwlek");
        mockMvc.perform(post("/users")
                .contentType("application/json")
                .param("sendWelcomeMail", "true")
                .content(objectMapper.writeValueAsString(user)))
                .andExpect(status().isBadRequest())
                .andExpect(MockMvcResultMatchers.jsonPath("$.msg").value("Validation Error"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.errors[0].message").value("Email is required!"));
    }

    @Test
    void deleteUserForbidden() throws Exception {
        User user = new User("Niwlek Niwlek", "Niwlek@galaxy.net", "NiwlekNiwlek");
        mockMvc.perform(post("/users")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(user)));

        mockMvc.perform(delete("/users/{id}", 1L)
                .contentType("application/json")
                .param("sendWelcomeMail", "true")
                .content(objectMapper.writeValueAsString(user)))
                .andExpect(status().isForbidden());
    }
}
