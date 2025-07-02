package com.github.gescof.springboottestingconcept.services;

import com.github.gescof.springboottestingconcept.exceptions.NotFoundException;
import com.github.gescof.springboottestingconcept.models.Example;
import com.github.gescof.springboottestingconcept.persistence.ExampleEntity;
import com.github.gescof.springboottestingconcept.persistence.ExampleRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ExampleServiceImplTest {

    private static final Random RANDOM = new Random();

    @Mock
    private ExampleRepository exampleRepository;

    private ExampleService exampleService;

    @BeforeEach
    void setUp() {
        exampleService = new ExampleServiceImpl(exampleRepository);
    }

    @Nested
    class WhenFetchExamples {

        private static Stream<Arguments> exampleEntitiesAndExpectedExamples() {
            List<ExampleEntity> exampleEntities1 = List.of(new ExampleEntity("example"));
            List<Example> expectedExamples1 = List.of(new Example(exampleEntities1.getFirst().getId(),
                    exampleEntities1.getFirst().getName()));

            List<ExampleEntity> exampleEntities2 = List.of();
            List<Example> expectedExamples2 = List.of();

            return Stream.of(
                    Arguments.of(exampleEntities1, expectedExamples1),
                    Arguments.of(exampleEntities2, expectedExamples2)
            );
        }

        @ParameterizedTest(name = "Given a list of example entities: {0}, should return a list of examples: {1}")
        @MethodSource("exampleEntitiesAndExpectedExamples")
        void shouldReturnExamples(List<ExampleEntity> exampleEntities, List<Example> expectedExamples) {
            // Arrange
            when(exampleRepository.findAll()).thenReturn(exampleEntities);

            // Act
            List<Example> result = exampleService.fetchExamples();

            // Assert
            assertThat(result)
                    .usingRecursiveComparison()
                    .isEqualTo(expectedExamples);
        }
    }

    @Nested
    class WhenFetchExample {

        private static Stream<Arguments> exampleEntityAndExpectedExample() {
            ExampleEntity exampleEntity1 = new ExampleEntity("example");
            Example expectedExample1 = new Example(exampleEntity1.getId(), exampleEntity1.getName());

            ExampleEntity exampleEntity2 = new ExampleEntity();
            Example expectedExample2 = new Example(exampleEntity2.getId(), exampleEntity2.getName());

            return Stream.of(
                    Arguments.of(exampleEntity1, expectedExample1),
                    Arguments.of(exampleEntity2, expectedExample2)
            );
        }

        @ParameterizedTest(name = "Given an example entity: {0}, should return an example: {1}")
        @MethodSource("exampleEntityAndExpectedExample")
        void shouldReturnExample(ExampleEntity exampleEntity, Example expectedExample) {
            // Arrange
            when(exampleRepository.findById(exampleEntity.getId())).thenReturn(Optional.of(exampleEntity));

            // Act
            Example result = exampleService.fetchExample(exampleEntity.getId());

            // Assert
            assertThat(result)
                    .usingRecursiveComparison()
                    .isEqualTo(expectedExample);
        }

        @Test
        void shouldThrowNotFoundException() {
            // Arrange
            Long id = RANDOM.nextLong();

            when(exampleRepository.findById(id)).thenReturn(Optional.empty());

            // Act & Assert
            assertThatThrownBy(() -> exampleService.fetchExample(id))
                    .isInstanceOf(NotFoundException.class)
                    .hasMessageContaining(String.format("Example with id %s not found", id));
        }
    }
}
