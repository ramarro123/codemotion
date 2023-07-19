package it.fabrick.client.controller;

import it.fabrick.client.model.Book;
import it.fabrick.client.model.Response;
import it.fabrick.client.repository.AuthorRepository;
import it.fabrick.client.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

import java.net.URISyntaxException;
import java.util.stream.Collectors;

@RequestMapping("/")
@RestController
@RequiredArgsConstructor
public class ClientController {

    final private BookRepository bookRepository;
    final private AuthorRepository authorRepository;

    WebClient webClient = WebClient.builder()
            .build();

    @GetMapping("/client/empty/{sleep}")
    @Transactional
    public Mono<ResponseEntity<Response>> empty(@PathVariable(required = false) int sleep) {
        return Mono.just(ResponseEntity.ok(new Response()));

    }

    @GetMapping("/client/{books}")
    public Mono<ResponseEntity<Response>> client(
            @RequestHeader(defaultValue = "100", name = "x-throttle") int sleep,
            @PathVariable(required = false) int books) throws URISyntaxException {


        return webClient.get()
                .uri("http://127.0.0.1:8080/server/" + books)
                .header("x-throttle", String.valueOf(sleep))
                .retrieve()
                .bodyToMono(Response.class)
                .flatMap(
                        s ->
                                Mono.fromCallable(() -> {
                                    Thread.sleep(10);
                                    return s;
                                }).publishOn(Schedulers.boundedElastic())

                )
                .map(ResponseEntity::ok);
    }

    @GetMapping("/client/db/{books}")
    public Flux<Book> db(
            @RequestHeader(defaultValue = "100", name = "x-throttle") int sleep,
            @PathVariable(required = false) int books) throws URISyntaxException {


        return webClient.get()
                .uri("http://127.0.0.1:8080/server/" + books)
                .header("x-throttle", String.valueOf(sleep))
                .retrieve()
                .bodyToMono(Response.class)
                .flatMapMany(
                        s ->
                                authorRepository.save(s.getAuthor())
                                        .flatMapMany(a ->
                                                bookRepository.saveAll(s.getBooks()
                                                        .stream().map(b -> b.setAuthorId(a.getId()))
                                                        .collect(Collectors.toList()))
                                        )
                );
    }
}
