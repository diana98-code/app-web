package com.distribuida.dao;


import com.distribuida.entity.Author;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.List;

import static org.springframework.http.HttpMethod.*;

@ApplicationScoped
public class AuthorDAOImpl implements AuthorDAO {

    private final String PATH_URL = "http://localhost:9090/api/author";

    @Inject
    RestTemplate restTemplate;

    @Override
    public List<Author> findAll() {
        var response = restTemplate.exchange(
                PATH_URL,
                GET,
                null,
                new ParameterizedTypeReference<List<Author>>() {
                }
        );
        return response.getBody();
    }

    @Override
    public Author findById(Long id) {
        ResponseEntity<Author> res = restTemplate.getForEntity(PATH_URL + "/" + id, Author.class);
        return res.getBody();
    }

    @Override
    public void deleteAuthor(Long id) {
        restTemplate.delete(PATH_URL + "/" + id);
    }

    @Override
    public void updateAuthor(Long id, Author author) {
        HttpEntity entity = new HttpEntity(author, this.headers());
        restTemplate.exchange(PATH_URL + "/" + id, PUT, entity, Author.class);
    }

    @Override
    public void createAuthor(Author author) {
        HttpEntity entity = new HttpEntity(author, this.headers());
        restTemplate.exchange(PATH_URL, POST, entity, Author.class);
    }

    public HttpHeaders headers() {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "application/json");
        return headers;
    }

}
