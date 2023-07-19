package it.fabrick.server.controller;

import it.fabrick.server.model.Author;
import it.fabrick.server.model.Book;
import it.fabrick.server.model.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;


@RequestMapping("/")
@RestController
public class ServerController {

    Logger logger = LoggerFactory.getLogger(ServerController.class);
    Long ct = System.currentTimeMillis();


    @GetMapping("/server/{books}")
    public Mono<ResponseEntity<Response>> server(@PathVariable(required = false) int books) {
        Response data = new Response();
        data.setAuthor(new Author(randomStr(), randomStr()));
        for (int i = 0; i < books; i++)
            data.addBook(new Book(randomStr()));
        return Mono.just(ResponseEntity.ok(data));

    }

    private String randomStr() {
        return Long.toString(
                ct++,
                Character.MAX_RADIX
        );
    }
}
