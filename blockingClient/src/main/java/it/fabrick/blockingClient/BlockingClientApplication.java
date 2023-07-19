package it.fabrick.blockingClient;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
public class BlockingClientApplication {

    public static void main(String[] args) {
        SpringApplication.run(BlockingClientApplication.class, args);
    }

}
