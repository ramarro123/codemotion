package it.fabrick.client.repository;

import it.fabrick.client.model.Author;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface AuthorRepository extends ReactiveCrudRepository<Author, Integer> {


}