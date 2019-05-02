package com.suom.graphql.services;

import com.google.common.base.Charsets;
import com.google.common.io.Resources;
import com.suom.graphql.dataFetchers.AllAuthorDataFetcher;
import com.suom.graphql.dataFetchers.ArticlesDataFetcher;
import com.suom.graphql.dataFetchers.AuthorDataFetcher;
import com.suom.graphql.models.Author;
import graphql.GraphQL;
import graphql.schema.DataFetcher;
import graphql.schema.GraphQLSchema;
import graphql.schema.idl.RuntimeWiring;
import graphql.schema.idl.SchemaGenerator;
import graphql.schema.idl.SchemaParser;
import graphql.schema.idl.TypeDefinitionRegistry;
import graphql.schema.idl.TypeRuntimeWiring;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.net.URL;
import java.util.Map;

@Component
public class GraphQLProvider {

    private GraphQL graphQL;

    @Autowired
    private ArticlesDataFetcher articlesDataFetcher;

    @Autowired
    private AuthorDataFetcher authorDataFetcher;

    @Autowired
    private AllAuthorDataFetcher allAuthorDataFetcher;

    @Bean
    public GraphQL graphQL() {
        return graphQL;
    }

    /**
     * initialize GraphQL instance
     *
     * @throws IOException
     */
    @PostConstruct
    public void init() throws IOException {
        URL url = Resources.getResource("schema.graphqls");
        String sdl = Resources.toString(url, Charsets.UTF_8);
        GraphQLSchema graphQLSchema = buildSchema(sdl);
        this.graphQL = GraphQL.newGraphQL(graphQLSchema).build();
    }

    private GraphQLSchema buildSchema(String sdl) {
        TypeDefinitionRegistry typeRegistry = new SchemaParser().parse(sdl);
        RuntimeWiring runtimeWiring = buildWiring();
        SchemaGenerator schemaGenerator = new SchemaGenerator();
        return schemaGenerator.makeExecutableSchema(typeRegistry, runtimeWiring);
    }

    private RuntimeWiring buildWiring() {
        return RuntimeWiring.newRuntimeWiring()
                .type(TypeRuntimeWiring.newTypeWiring("Query").dataFetcher("author", authorDataFetcher))
                .type(TypeRuntimeWiring.newTypeWiring("Author").dataFetcher("name", getNameDateFetcher()))
                .type(TypeRuntimeWiring.newTypeWiring("Query").dataFetcher("authors", allAuthorDataFetcher))
                .type(TypeRuntimeWiring.newTypeWiring("Query").dataFetcher("allAuthorArticles", articlesDataFetcher)).build();
    }

    private DataFetcher getNameDateFetcher() {
        return environment -> {
            Author author = environment.getSource();
            return author.getFullName();
        };
    }

}