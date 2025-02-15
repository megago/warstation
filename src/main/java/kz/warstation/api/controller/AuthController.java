package kz.warstation.api.controller;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @PostMapping("/jwt/login")
    public ResponseEntity<Map<String, String>> login(@RequestParam String username, @RequestParam String password) {
        Map<String, String> response = new HashMap<>();
        response.put("access_token", "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIzN2UyMmZhMi01NmViLTRmNTgtYWNmZC1kOWUyZThlMTkzMDkiLCJhdWQiOlsiZmFzdGFwaS11c2VyczphdXRoIl0sImV4cCI6MTc2OTY5MjQwMX0.eKt6KDohbf8UDNmyLNkgKnOhNvIJ84GwF1hgRDW4YH0");
        response.put("token_type", "bearer");

        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONNECTION, "keep-alive");
        headers.add(HttpHeaders.CONTENT_LENGTH, "245");
        headers.add(HttpHeaders.CONTENT_TYPE, "application/json");
        headers.add("Date", ZonedDateTime.now().format(DateTimeFormatter.RFC_1123_DATE_TIME));
        headers.add(HttpHeaders.SERVER, "nginx/1.16.1");

        return new ResponseEntity<>(response, headers, HttpStatus.OK);
    }
}