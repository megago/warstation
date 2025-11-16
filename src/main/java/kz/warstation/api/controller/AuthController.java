package kz.warstation.api.controller;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @PostMapping("/jwt/login")
    public ResponseEntity<String> login(@RequestParam String username, @RequestParam String password) {
        String jsonResponse = "{\"access_token\":\"eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIzN2UyMmZhMi01NmViLTRmNTgtYWNmZC1kOWUyZThlMTkzMDkiLCJhdWQiOlsiZmFzdGFwaS11c2VyczphdXRoIl0sImV4cCI6MTc3NTIxODMwMH0.OXn9YvotxziuTlCgDE0hABWcUkv5JsPry41N7YXaOBM\",\"token_type\":\"bearer\"}";

        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONNECTION, "keep-alive");
        headers.add(HttpHeaders.CONTENT_LENGTH, "245");
        headers.add(HttpHeaders.CONTENT_TYPE, "application/json");
        headers.add("date", "Sun, 05 Oct 2025 16:52:02 GMT");
        headers.add("x-sign", "MEQCIBzH+M9cWlb24XzTR4QJSgQ7izmPCBNRbY73DIT9g7zJAiBiP4TUd4Aj31GufXce7XRBzTiX+k3egqjKm2gsJDKqOQ==");
        headers.add(HttpHeaders.SERVER, "nginx/1.16.12");

        return new ResponseEntity<>(jsonResponse, headers, HttpStatus.OK);
    }
}