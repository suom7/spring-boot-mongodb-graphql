package com.suom.graphql.dataFetchers;

import com.suom.graphql.models.Article;
import com.suom.graphql.services.ArticleService;
import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public class ArticlesDataFetcher implements DataFetcher<List<Article>> {

    @Autowired
    private ArticleService articleService;

    @Override
    public List<Article> get(DataFetchingEnvironment env) {
        Map args = env.getArguments();
        return articleService.findByAuthorId(new ObjectId(String.valueOf(args.get("authorId"))));
    }
}
