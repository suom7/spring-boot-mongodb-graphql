package com.suom.graphql.dataFetchers;

import com.suom.graphql.models.Author;
import com.suom.graphql.services.AuthorService;
import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class AuthorDataFetcher implements DataFetcher<Author> {

    @Autowired
    private AuthorService authorService;

    @Override
    public Author get(DataFetchingEnvironment env) {
        Map args = env.getArguments();
        return authorService.findOneById(new ObjectId(String.valueOf(args.get("id"))));
    }
}
