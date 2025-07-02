package com.github.gescof.springboottestingconcept.services;

import com.github.gescof.springboottestingconcept.models.Example;
import com.github.gescof.springboottestingconcept.persistence.ExampleEntity;
import com.github.gescof.springboottestingconcept.persistence.ExampleRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
class ExampleServiceImplIT {

    @MockitoBean
    private ExampleRepository exampleRepository;

    private ExampleService exampleService;

    @BeforeEach
    void setUp() {
        exampleService = new ExampleServiceImpl(exampleRepository);
    }

    @Test
    void shouldReturnExamples() {
        // Arrange
        List<ExampleEntity> exampleEntities = List.of(new ExampleEntity("example"));

        when(exampleRepository.findAll()).thenReturn(exampleEntities);

        // Act
        List<Example> result = exampleService.fetchExamples();

        // Assert
        assertThat(result).hasSize(1);
    }

    @Test
    void shouldReturnExample() {
        // Arrange
        ExampleEntity exampleEntity = new ExampleEntity("example");

        when(exampleRepository.findById(exampleEntity.getId())).thenReturn(Optional.of(exampleEntity));

        // Act
        Example result = exampleService.fetchExample(exampleEntity.getId());

        // Assert
        assertThat(result).isNotNull();
    }
}
