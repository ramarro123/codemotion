package it.fabrick.server.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;
import reactor.netty.http.server.HttpServerConfig;

import java.time.Duration;

@Component
public class WebFluxFilter implements org.springframework.web.server.WebFilter {
    static Logger logger = LoggerFactory.getLogger(WebFluxFilter.class);

    @Override
    public Mono filter(ServerWebExchange serverWebExchange, WebFilterChain webFilterChain) {

        Integer sleep = parseWithDefault(
                serverWebExchange.getRequest().getHeaders().getFirst("x-throttle"),
                100
        );

        return Mono
                .delay(Duration.ofMillis(sleep))
                .then(
                        webFilterChain.filter(serverWebExchange)
                );
    }

    private int parseWithDefault(String tok, int def) {
        try {
            return tok == null ? def : Integer.parseInt(tok);
        } catch (Throwable e) {
            logger.error("unable to parse " + tok, e);
        }
        return def;
    }
}
