package com.suom.graphql.mock;

import com.suom.graphql.models.Article;
import com.suom.graphql.models.Author;
import com.suom.graphql.repositories.ArticleRepository;
import com.suom.graphql.repositories.AuthorRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component
@Slf4j
public class MockData {

    @Autowired
    private AuthorRepository userRepository;

    @Autowired
    private ArticleRepository articleRepository;

    private static final Long ZERO_RECORD = 0L;

    @PostConstruct
    private void generateData() {
        final long count = userRepository.count();
        log.info(String.format("Count %d", count));
        if (ZERO_RECORD.equals(count)) {
            log.info("============ Initialize mock data ===============");
            List<Author> authors = new ArrayList<>();
            authors.add(Author.builder().fullName("author-1").createdAt(new Date()).age(24).nationality("Cambodia").build());
            authors.add(Author.builder().fullName("author-2").createdAt(new Date()).age(24).nationality("Cambodia").build());
            authors.add(Author.builder().fullName("author-3").createdAt(new Date()).age(53).nationality("Cambodia").build());
            authors.add(Author.builder().fullName("author-4").createdAt(new Date()).age(14).nationality("Cambodia").build());
            authors = userRepository.saveAll(authors);

            final List<Article> articles = new ArrayList<>();
            articles.add(Article.builder().title("Java 8 Lambdas").minutesRead(8).authorId(authors.get(0).getId()).build());
            articles.add(Article.builder().title("GraphQL Getting Started").minutesRead(10).authorId(authors.get(1).getId()).build());
            articles.add(Article.builder().title("Spring Boot + WebSockets").minutesRead(6).authorId(authors.get(2).getId()).build());
            articles.add(Article.builder().title("Spring Boot + MongoDB").minutesRead(20).authorId(authors.get(3).getId()).build());
            articleRepository.saveAll(articles);
        }
    }
}
