package it.fabrick.client.model;

import lombok.Data;
import org.springframework.data.annotation.Id;

@Data
public class Author {

    @Id
    Integer id;

    String firstName;

    String lastName;

    protected Author() {
    }

    public Author(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

}
