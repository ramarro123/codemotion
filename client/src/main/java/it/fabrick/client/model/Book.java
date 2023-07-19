package it.fabrick.client.model;

import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.Id;

@Data
@Accessors(chain = true)
public class Book {

    @Id
    Integer id;

    String bookTitle;

    Integer authorId;

    protected Book() {
    }

    public Book(String bookTitle, Integer authorId) {
        this.bookTitle = bookTitle;
        this.authorId = authorId;
    }

}
