package it.fabrick.client.repository;

import it.fabrick.client.model.Book;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;

public interface BookRepository extends ReactiveCrudRepository<Book, Integer> {

    @Query("SELECT * FROM book WHERE author_id = :id")
    Flux<Book> findBooksBy(Integer id);


}