package com.suom.graphql.services.implementation;

import com.suom.graphql.models.Author;
import com.suom.graphql.repositories.AuthorRepository;
import com.suom.graphql.services.AuthorService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthorServiceImpl implements AuthorService {

    @Autowired
    private AuthorRepository repository;

    @Override
    public List<Author> findAllAuthors() {
        return repository.findAll();
    }

    @Override
    public Author findOneById(ObjectId id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public Iterable<Author> findByIdIn(Iterable<ObjectId> ids) {
        return repository.findAllById(ids);
    }
}
