package it.fabrick.blockingClient.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.experimental.Accessors;




@Data
@Accessors(chain = true)
@Entity
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    Integer id;

    String bookTitle;

    int authorId;

    protected Book() {
    }

    public Book(String bookTitle, int authorId) {
        this.bookTitle = bookTitle;
        this.authorId = authorId;
    }

}
