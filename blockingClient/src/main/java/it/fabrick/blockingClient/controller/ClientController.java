package it.fabrick.blockingClient.controller;

import it.fabrick.blockingClient.model.Author;
import it.fabrick.blockingClient.model.Book;
import it.fabrick.blockingClient.model.Response;
import it.fabrick.blockingClient.repository.AuthorRepository;
import it.fabrick.blockingClient.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.stream.Collectors;


@RequestMapping("/")
@RestController
@RequiredArgsConstructor
public class ClientController {
    final RestTemplate client = new RestTemplate();
    final private BookRepository bookRepository;
    final private AuthorRepository authorRepository;


    @GetMapping("/client/{books}")
    public ResponseEntity<Response> client(@RequestHeader(defaultValue = "100", name = "x-throttle") int sleep,
                                           @PathVariable(required = false) int books) {

        HttpHeaders headers = new HttpHeaders();
        headers.add("x-throttle", String.valueOf(sleep));
        HttpEntity<String> httpEntity = new HttpEntity<>(headers);

        ResponseEntity<Response> t = client
                .exchange("http://127.0.0.1:8080/server/" + books,
                        HttpMethod.GET,
                        httpEntity,
                        Response.class);
        //check
        return ResponseEntity.ok(t.getBody());
    }

    @GetMapping("/client/db/{books}")
    public ResponseEntity<List<Book>> db(@RequestHeader(defaultValue = "100", name = "x-throttle") int sleep,
                                         @PathVariable(required = false) int books) {

        HttpHeaders headers = new HttpHeaders();
        headers.add("x-throttle", String.valueOf(sleep));
        HttpEntity<String> httpEntity = new HttpEntity<>(headers);

        ResponseEntity<Response> t = client
                .exchange("http://127.0.0.1:8080/server/" + books,
                        HttpMethod.GET,
                        httpEntity,
                        Response.class);


        //check
        Author a = authorRepository.save(t.getBody().getAuthor());

        //check
        List<Book> ret = t.getBody().getBooks().stream().map(s -> s.setAuthorId(a.getId())).collect(Collectors.toList());

        bookRepository.saveAll(ret);

        return ResponseEntity.ok(ret);
    }

}
