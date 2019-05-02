package com.suom.graphql.repositories;

import com.suom.graphql.models.Article;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ArticleRepository extends MongoRepository<Article, ObjectId> {
    List<Article> findByAuthorId(ObjectId authorId);
}
