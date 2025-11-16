package kz.warstation.api.controller;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/avatargames")
public class YandexCludConfigController {

    @GetMapping("/config.json")
    public ResponseEntity<String> config(@RequestParam String username, @RequestParam String password) {
        String jsonResponse = "{\n" +
                "    \"license\": \"1\"\n" +
                "}";

        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONNECTION, "keep-alive");
        headers.add(HttpHeaders.CONTENT_TYPE, "application/json");
        headers.add("date", "thu, 18 sep 2025 17:19:16 gmt");
        headers.add("etag", "w/\"c3bd80b15f2cd5de5893e5cf74727d1f\"");
        headers.add("last-modified", "mon, 28 jul 2025 10:36:07 gmt");
        headers.add("transfer-encoding", "chunked");
        headers.add("x-amz-request-id", "722ffec74429222d");
        headers.add(HttpHeaders.SERVER, "nginx");

        return new ResponseEntity<>(jsonResponse, headers, HttpStatus.OK);
    }
}