package com.github.gescof.springboottestingconcept.services;

import com.github.gescof.springboottestingconcept.exceptions.NotFoundException;
import com.github.gescof.springboottestingconcept.models.Example;
import com.github.gescof.springboottestingconcept.persistence.ExampleRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExampleServiceImpl implements ExampleService {

    private final ExampleRepository exampleRepository;

    public ExampleServiceImpl(ExampleRepository exampleRepository) {
        this.exampleRepository = exampleRepository;
    }

    @Override
    public List<Example> fetchExamples() {
        return exampleRepository.findAll().stream()
                .map(exampleEntity -> new Example(exampleEntity.getId(), exampleEntity.getName()))
                .toList();
    }

    @Override
    public Example fetchExample(Long id) {
        return exampleRepository.findById(id)
                .map(exampleEntity -> new Example(exampleEntity.getId(), exampleEntity.getName()))
                .orElseThrow(() -> new NotFoundException(String.format("Example with id %s not found", id)));
    }
}
