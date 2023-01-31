package com.distribuida.dao;


import com.distribuida.entity.Book;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.List;

import static org.springframework.http.HttpMethod.*;

@ApplicationScoped
public class BookDAOImpl implements BookDAO {

    private final String PATH_URL = "http://loadbalancer/book";

    @Override
    public List<Book> findAll() {
        RestTemplate restTemplate = new RestTemplate();
        var response = restTemplate
                .exchange(PATH_URL, GET, null, new ParameterizedTypeReference<List<Book>>() {
                });
        return response.getBody();
    }

    @Override
    public Book findById(int id) {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Book> res = restTemplate.getForEntity(PATH_URL + "/" + id, Book.class);
        return res.getBody();
    }

    @Override
    public void deleteBook(int id) {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.delete(PATH_URL + "/" + id);
    }

    @Override
    public void updateBook(int id, Book book) {
        RestTemplate restTemplate = new RestTemplate();
        HttpEntity entity = new HttpEntity(book, this.headers());
        restTemplate.exchange(PATH_URL + "/" + id, PUT, entity, Book.class);
    }

    @Override
    public void createBook(Book book) {
        RestTemplate restTemplate = new RestTemplate();
        HttpEntity entity = new HttpEntity(book, this.headers());
        restTemplate.exchange(PATH_URL, POST, entity, Book.class);
    }

    public HttpHeaders headers() {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "application/json");
        return headers;
    }

}
