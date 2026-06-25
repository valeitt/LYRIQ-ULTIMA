package cl.lyriq.api_gateway.security;

import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;

import reactor.core.publisher.Mono;

/**
 * Filtro JWT desactivado.
 * Todas las peticiones pasan sin autenticación.
 * La validación JWT queda deshabilitada en este proyecto.
 */
@Component
public class JwtAuthenticationFilter implements GlobalFilter {

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        // Sin autenticación: todas las peticiones pasan directamente
        return chain.filter(exchange);
    }
}