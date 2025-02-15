package kz.warstation.api.config;

import io.netty.handler.ssl.SslContextBuilder;
import org.springframework.boot.web.embedded.netty.NettyReactiveWebServerFactory;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import reactor.netty.http.Http11SslContextSpec;

import javax.net.ssl.KeyManagerFactory;
import java.io.File;
import java.io.FileInputStream;
import java.security.KeyStore;

//@Configuration
public class NettyHttpRedirectConfig {

//    @Bean
    public WebServerFactoryCustomizer<NettyReactiveWebServerFactory> nettyCustomizer() {
        return factory -> factory.addServerCustomizers(httpServer -> {
            try {
                File keyStoreFile = new File("src/main/resources/certs/server.pfx");
                String keyStorePassword = "changeit";

                KeyStore keyStore = KeyStore.getInstance("PKCS12");
                try (FileInputStream fis = new FileInputStream(keyStoreFile)) {
                    keyStore.load(fis, keyStorePassword.toCharArray());
                }

                KeyManagerFactory keyManagerFactory = KeyManagerFactory.getInstance(KeyManagerFactory.getDefaultAlgorithm());
                keyManagerFactory.init(keyStore, keyStorePassword.toCharArray());

                Http11SslContextSpec sslContextSpec = Http11SslContextSpec.forServer(keyManagerFactory);

                return httpServer.secure(ssl -> ssl.sslContext(sslContextSpec));
            } catch (Exception e) {
                throw new RuntimeException("Failed to configure SSL", e);
            }
        });
    }
}
