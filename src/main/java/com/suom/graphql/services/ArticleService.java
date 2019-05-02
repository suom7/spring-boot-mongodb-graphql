package com.suom.graphql.services;

import com.suom.graphql.models.Article;
import org.bson.types.ObjectId;

import java.util.List;

public interface ArticleService {
    List<Article> findByAuthorId(ObjectId authorId);
}
