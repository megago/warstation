package kz.warstation.api.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.*;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;
import reactor.netty.http.client.HttpClient;
import io.netty.handler.ssl.SslContext;
import io.netty.handler.ssl.SslContextBuilder;
import javax.net.ssl.TrustManagerFactory;
import java.security.KeyStore;
import java.io.InputStream;
import java.security.cert.CertificateException;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import java.security.NoSuchAlgorithmException;
import java.security.KeyManagementException;
import java.security.cert.X509Certificate;
import java.util.Collections;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

@RestController
//@RequestMapping("/")
public class ProxyController {

    private final WebClient webClient;

    public ProxyController(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder
                .clientConnector(new ReactorClientHttpConnector(
                        HttpClient.create().secure(ssl -> ssl.sslContext(disableSSLVerification()))
                ))
                .baseUrl("https://localhost")
                .build();
    }

//    private final RestTemplate restTemplate = new RestTemplate();
//
//    @RequestMapping(value = "/**", method = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
//    public ResponseEntity<String> proxyRequest(HttpServletRequest request, @RequestBody(required = false) String body) {
//        String targetUrl = "https://localhost:8081" + request.getRequestURI(); // Ensure it forwards to HTTPS
//
//        HttpHeaders headers = Collections.list(request.getHeaderNames()).stream()
//                .collect(HttpHeaders::new, (h, name) -> h.set(name, request.getHeader(name)), HttpHeaders::putAll);
//
//        HttpEntity<String> entity = new HttpEntity<>(body, headers);
//
//        ResponseEntity<String> response = restTemplate.exchange(targetUrl, HttpMethod.valueOf(request.getMethod()), entity, String.class);
//
//        return new ResponseEntity<>(response.getBody(), response.getStatusCode());
//    }
//    @RequestMapping(value = "/**", method = { RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE, RequestMethod.PATCH })
    public Mono<ResponseEntity<byte[]>> proxyRequest(ServerWebExchange exchange) {
        String targetUrl = "https://localhost" + exchange.getRequest().getPath().toString();

        System.out.println("🔹 Proxying request to: " + targetUrl);

        return webClient.method(exchange.getRequest().getMethod())
                .uri(targetUrl)
                .headers(headers -> headers.addAll(exchange.getRequest().getHeaders()))
                .body(exchange.getRequest().getBody(), byte[].class)
                .exchangeToMono(clientResponse ->
                        clientResponse.bodyToMono(byte[].class)
                                .map(body -> ResponseEntity.status(clientResponse.statusCode())
                                        .headers(headers -> headers.addAll(clientResponse.headers().asHttpHeaders()))
                                        .body(body))
                );
    }

    private static SslContext disableSSLVerification() {
        try {
            TrustManagerFactory trustManagerFactory = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
            trustManagerFactory.init((KeyStore) null);

            TrustManager[] trustManagers = trustManagerFactory.getTrustManagers();
            X509TrustManager x509TrustManager = (X509TrustManager) trustManagers[0];

            return SslContextBuilder.forClient()
                    .trustManager(x509TrustManager)
                    .build();

        } catch (Exception e) {
            throw new RuntimeException("Failed to create Netty SSL context", e);
        }
    }
}
