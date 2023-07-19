package it.fabrick.blockingClient.repository;


import it.fabrick.blockingClient.model.Author;
import org.springframework.data.repository.CrudRepository;

public interface AuthorRepository extends CrudRepository<Author, Integer> {


}