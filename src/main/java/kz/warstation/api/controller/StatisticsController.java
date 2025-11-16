package kz.warstation.api.controller;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/round-statistics")
public class StatisticsController {

    @PostMapping
    public ResponseEntity<String> postRoundStatistics(@RequestBody Map<String, Object> requestBody) {
        String jsonResponse = "{\n" +
                "  \"success\": true\n" +
                "}";

        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONNECTION, "keep-alive");
        headers.add(HttpHeaders.CONTENT_LENGTH, "23");
        headers.add(HttpHeaders.CONTENT_TYPE, "application/json");
        headers.add("date", "Tue, 28 Jan 2025 17:05:08 GMT");
        headers.add(HttpHeaders.SERVER, "nginx/1.22.1");

        return new ResponseEntity<>(jsonResponse, headers, HttpStatus.OK);
    }
}