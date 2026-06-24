package cl.lyriq.api_gateway.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;

import reactor.core.publisher.Mono;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;

@Component
public class JwtAuthenticationFilter
        implements GlobalFilter {

    @Value("${jwt.secret}")
    private String secretKey;

    @Override
    public Mono<Void> filter(
            ServerWebExchange exchange,
            GatewayFilterChain chain) {

        String path =
                exchange.getRequest()
                        .getURI()
                        .getPath();

        // Rutas públicas
        if (path.startsWith("/auth")
                || path.startsWith("/swagger-ui")
                || path.startsWith("/v3/api-docs")
                || path.contains("/v3/api-docs")) {

            return chain.filter(exchange);
        }

        String authHeader =
                exchange.getRequest()
                        .getHeaders()
                        .getFirst(HttpHeaders.AUTHORIZATION);

        if (authHeader == null
                || !authHeader.startsWith("Bearer ")) {

            exchange.getResponse()
                    .setStatusCode(HttpStatus.UNAUTHORIZED);

            return exchange.getResponse()
                    .setComplete();
        }

        String token =
                authHeader.replace("Bearer ", "");

        try {

            SecretKey key =
                    Keys.hmacShaKeyFor(
                            secretKey.getBytes(
                                    StandardCharsets.UTF_8
                            )
                    );

            Claims claims =
                    Jwts.parser()
                            .verifyWith(key)
                            .build()
                            .parseSignedClaims(token)
                            .getPayload();

        } catch (Exception e) {

            exchange.getResponse()
                    .setStatusCode(HttpStatus.UNAUTHORIZED);

            return exchange.getResponse()
                    .setComplete();
        }

        return chain.filter(exchange);
    }
}