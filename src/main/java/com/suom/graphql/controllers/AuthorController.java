package com.suom.graphql.controllers;

import graphql.ExecutionResult;
import graphql.GraphQL;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class AuthorController {

    @Autowired
    private GraphQL graphQL;

    @PostMapping(value = "/query")
    public ResponseEntity query(@RequestBody String query) {
        ExecutionResult result = graphQL.execute(query);
        log.error("====== errors: " + result.getErrors());
        return ResponseEntity.ok(result.getData());
    }

}
