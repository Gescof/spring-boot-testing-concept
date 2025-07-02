package com.github.gescof.springboottestingconcept.controllers;

import com.github.gescof.springboottestingconcept.models.GetExampleResponse;
import com.github.gescof.springboottestingconcept.models.GetExamplesResponse;
import com.github.gescof.springboottestingconcept.services.ExampleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/examples")
public class ExampleController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ExampleController.class);

    private final ExampleService exampleService;

    public ExampleController(ExampleService exampleService) {
        this.exampleService = exampleService;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public GetExamplesResponse getExamples() {
        LOGGER.info("Fetching all examples");
        return new GetExamplesResponse(exampleService.fetchExamples());
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public GetExampleResponse getExample(@PathVariable Long id) {
        LOGGER.info("Fetching example with id {}", id);
        return new GetExampleResponse(exampleService.fetchExample(id));
    }
}
