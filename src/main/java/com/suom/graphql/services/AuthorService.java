package com.suom.graphql.services;

import com.suom.graphql.models.Author;
import org.bson.types.ObjectId;

import java.util.List;

public interface AuthorService {

    List<Author> findAllAuthors();

    Author findOneById(ObjectId id);

    Iterable<Author> findByIdIn(Iterable<ObjectId> ids);
}
