package com.suom.graphql.dataFetchers;

import com.suom.graphql.models.Author;
import com.suom.graphql.services.AuthorService;
import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AllAuthorDataFetcher implements DataFetcher<List<Author>> {
    @Autowired
    private AuthorService authorService;

    @Override
    public List<Author> get(DataFetchingEnvironment environment) {
        return authorService.findAllAuthors();
    }
}
