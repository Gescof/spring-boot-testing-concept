package com.github.gescof.springboottestingconcept.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.gescof.springboottestingconcept.exceptions.NotFoundException;
import com.github.gescof.springboottestingconcept.models.Example;
import com.github.gescof.springboottestingconcept.models.GetExampleResponse;
import com.github.gescof.springboottestingconcept.models.GetExamplesResponse;
import com.github.gescof.springboottestingconcept.services.ExampleService;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.Random;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ExampleController.class)
class ExampleControllerIT {

    private static final Random RANDOM = new Random();

    @MockitoBean
    private ExampleService exampleService;

    @Autowired
    private MockMvc mockMvc;

    @Nested
    class WhenGetExamples {

        @Test
        void shouldGetExamplesWithSuccess() throws Exception {
            // Arrange
            List<Example> examples = List.of(new Example(RANDOM.nextLong(), "example"));
            String expectedContent = new ObjectMapper().writeValueAsString(new GetExamplesResponse(examples));

            when(exampleService.fetchExamples()).thenReturn(examples);

            // Act & Assert
            mockMvc.perform(get("/examples"))
                    .andExpect(status().isOk())
                    .andExpect(content().string(expectedContent));
        }
    }

    @Nested
    class WhenGetExample {

        @Test
        void shouldGetExampleWithSuccess() throws Exception {
            // Arrange
            Example example = new Example(RANDOM.nextLong(), "example");
            String expectedContent = new ObjectMapper().writeValueAsString(new GetExampleResponse(example));

            when(exampleService.fetchExample(example.id())).thenReturn(example);

            // Act & Assert
            mockMvc.perform(get("/examples/{id}", example.id()))
                    .andExpect(status().isOk())
                    .andExpect(content().string(expectedContent));
        }

        @Test
        void shouldGetNotFound() throws Exception {
            // Arrange
            Long id = RANDOM.nextLong();

            when(exampleService.fetchExample(id)).thenThrow(NotFoundException.class);

            // Act & Assert
            mockMvc.perform(get("/examples/{id}", id))
                    .andExpect(status().isNotFound());
        }
    }
}
