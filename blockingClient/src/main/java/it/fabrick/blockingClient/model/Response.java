package it.fabrick.blockingClient.model;

import lombok.Data;

import java.util.LinkedList;
import java.util.List;

@Data
public class Response {
    Author author;
    List<Book> books = new LinkedList<>();

    public void addBook(Book book) {
        books.add(book);
    }
}
