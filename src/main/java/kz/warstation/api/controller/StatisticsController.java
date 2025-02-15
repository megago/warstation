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
@RequestMapping("/round-statistics")
public class StatisticsController {

    @PostMapping
    public ResponseEntity<Map<String, Boolean>> postRoundStatistics(@RequestBody Map<String, Object> requestBody) {
        Map<String, Boolean> response = new HashMap<>();
        response.put("success", true);

        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONNECTION, "keep-alive");
        headers.add(HttpHeaders.CONTENT_LENGTH, "16");
        headers.add(HttpHeaders.CONTENT_TYPE, "application/json");
        headers.add("Date", ZonedDateTime.now().format(DateTimeFormatter.RFC_1123_DATE_TIME));
        headers.add(HttpHeaders.SERVER, "nginx/1.22.1");

        return new ResponseEntity<>(response, headers, HttpStatus.OK);
    }
}