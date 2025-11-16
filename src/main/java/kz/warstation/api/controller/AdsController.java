package kz.warstation.api.controller;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/ads")
public class AdsController {

    @GetMapping
    public ResponseEntity<String> ads() {
        String jsonResponse = "{\"config\":{\"lobbyBanner\":\"https://storage.yandexcloud.net/avatargames/BannerLobbyVegas.png\",\"enabledGlobal\":true},\"created_at\":\"2025-03-21T09:40:52.152220Z\",\"updated_at\":\"2025-04-10T14:14:33.631311Z\"}";

        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_LENGTH, "200");
        headers.add(HttpHeaders.CONNECTION, "keep-alive");
        headers.add(HttpHeaders.CONTENT_TYPE, "application/json");
        headers.add("date", "Sun, 05 Oct 2025 16:52:03 GMT");
        headers.add("x-sign", "MEYCIQDHnBm6+IAZ+X2O03bqSvYnexfhR+I8vL8TKjyUO6aYvQIhAPM1okrzdX77mG4OSfJOmTId8hPClpkVbTgksNzZ5ln+");
        headers.add(HttpHeaders.SERVER, "nginx/1.20.2");

        return new ResponseEntity<>(jsonResponse, headers, HttpStatus.OK);
    }
}