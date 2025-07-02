package com.github.gescof.springboottestingconcept.services;

import com.github.gescof.springboottestingconcept.models.Example;

import java.util.List;

public interface ExampleService {

    List<Example> fetchExamples();

    Example fetchExample(Long id);
}
