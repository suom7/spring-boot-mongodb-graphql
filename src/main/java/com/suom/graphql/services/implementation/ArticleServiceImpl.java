package com.suom.graphql.services.implementation;

import com.suom.graphql.models.Article;
import com.suom.graphql.repositories.ArticleRepository;
import com.suom.graphql.services.ArticleService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArticleServiceImpl implements ArticleService {

    @Autowired
    private ArticleRepository repository;

    @Override
    public List<Article> findByAuthorId(ObjectId authorId) {
        return repository.findByAuthorId(authorId);
    }
}
