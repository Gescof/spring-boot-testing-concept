package com.github.gescof.springboottestingconcept.persistence;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class ExampleRepositoryIT {

    @Autowired
    private ExampleRepository exampleRepository;

    @Test
    void shouldFindAll() {
        // Arrange
        ExampleEntity exampleEntity = exampleRepository.save(new ExampleEntity("example"));

        // Act
        List<ExampleEntity> result = exampleRepository.findAll();

        // Assert
        assertThat(result).contains(exampleEntity);
    }

    @Test
    void shouldFindById() {
        // Arrange
        ExampleEntity exampleEntity = exampleRepository.save(new ExampleEntity("example"));

        // Act
        ExampleEntity result = exampleRepository.findById(exampleEntity.getId()).get();

        // Assert
        assertThat(result).isEqualTo(exampleEntity);
    }
}
