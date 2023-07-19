package it.fabrick.server.model;

import lombok.Data;

@Data
public class Book {


    Integer id;

    String bookTitle;

    Integer authorId;

    protected Book() {
    }

    public Book(String bookTitle) {
        this.bookTitle = bookTitle;
    }



}
