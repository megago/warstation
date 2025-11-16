package kz.warstation.api.controller;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/warstation/servers")
public class ServerController {

    @PostMapping("/add-hash")
    public ResponseEntity<Map<String, Boolean>> addServerHash(@RequestBody Map<String, String> requestBody) {
        Map<String, Boolean> response = new HashMap<>();
        response.put("success", true);

        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONNECTION, "keep-alive");
        headers.add(HttpHeaders.CONTENT_LENGTH, "16");
        headers.add(HttpHeaders.CONTENT_TYPE, "application/json");
        headers.add("date", "Sun, 05 Oct 2025 16:52:02 GMT");
        headers.add("x-sign", "MEQCIGUi2WWLNxOZ5p7ekD95s7tx5qsm0BHDZDEFygbUE6zXAiAL4ptGypNS25sr7G6m/bG3r1BNcSJQzK3cPdU9o/1OPQ==");
        headers.add(HttpHeaders.SERVER, "nginx/1.20.2");

        return new ResponseEntity<>(response, headers, HttpStatus.OK);
    }
}