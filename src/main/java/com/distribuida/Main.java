package com.distribuida;

import com.distribuida.controller.AuthorController;
import com.distribuida.controller.BookController;
import com.distribuida.entity.Author;
import com.distribuida.entity.Book;
import com.distribuida.dao.AuthorDAO;
import com.distribuida.dao.BookDAO;
import jakarta.enterprise.inject.Instance;
import jakarta.enterprise.inject.se.SeContainer;
import jakarta.enterprise.inject.se.SeContainerInitializer;
import spark.ModelAndView;
import spark.template.thymeleaf.ThymeleafTemplateEngine;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static spark.Spark.get;
import static spark.Spark.post;

public class Main {

    public static void main(String[] args) {
        SeContainer container;
        container = SeContainerInitializer.newInstance().initialize();
        AuthorController.pagesAuthor(container);
        BookController.pagesBook(container);
    }

}
