package it.fabrick.server.model;

import lombok.Data;

@Data
public class Author {

    Integer id;

    String firstName;

    String lastName;


    public Author(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

}
