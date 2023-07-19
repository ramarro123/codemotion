package it.fabrick.blockingClient.repository;

import it.fabrick.blockingClient.model.Book;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface BookRepository extends CrudRepository<Book, Integer> {

    @Query(value = "SELECT * FROM book WHERE author_id = :id", nativeQuery = true)
    Iterable<Book> findBooksBy(int id);


}