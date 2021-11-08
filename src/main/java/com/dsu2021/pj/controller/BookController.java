package com.dsu2021.pj.controller;

import org.springframework.web.bind.annotation.GetMapping;

public class BookController {

    @GetMapping("/")
    public String mainPage() {
        return "bookList";
    }
}
