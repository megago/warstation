package kz.warstation.api.controller;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.zip.GZIPOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

@RestController
@RequestMapping("/warstation/accounts")
public class ProfileController {

    @GetMapping("/my")
    public ResponseEntity<byte[]> getAccountInfo() throws IOException {
        String jsonResponse = """
            {
              "id": 729,
              "phone": "+7 705 257 2475",
              "city": "Казахстан",
              "address": "",
              "name": "Руслан ",
              "site": "",
              "franchise_tarif": [
                { "id": 7, "name": "+Party", "is_outdated": false, "franchise": { "id": 2, "name": "AvatarGames" } },
                { "id": 8, "name": "+Party2", "is_outdated": false, "franchise": { "id": 2, "name": "AvatarGames" } },
                { "id": 9, "name": "+Survival", "is_outdated": false, "franchise": { "id": 2, "name": "AvatarGames" } },
                { "id": 10, "name": "+Battle", "is_outdated": false, "franchise": { "id": 2, "name": "AvatarGames" } },
                { "id": 17, "name": "+Tactics", "is_outdated": false, "franchise": { "id": 2, "name": "AvatarGames" } },
                { "id": 19, "name": "+Horror", "is_outdated": false, "franchise": { "id": 2, "name": "AvatarGames" } }
              ],
              "has_subscription": false,
              "subscription_payment": 15000,
              "subscription_end_date": null,
              "per_minute_billing": false,
              "total_minutes_paid": 0,
              "total_minutes_spent": 10,
              "minutes_counted_until": "2025-01-28T20:13:16.467337Z",
              "last_minutes_top_up_at": "1970-01-01T00:00:00Z",
              "owner": { "id": "37e22fa2-56eb-4f58-acfd-d9e2e8e19309", "email": "Asirbekovruslan015@gmail.com", "is_active": true, "is_superuser": false, "is_verified": false },
              "manager": { "id": 1, "name": "Никита Афонычев", "phone": "+7 (950) 701-22-11", "email": "v2b@warstation.com, Info@avatargames.ru", "created_at": "2023-09-15T08:21:50.788441Z", "updated_at": "2023-09-15T08:21:50.788472Z" }
            }
            """;

        byte[] compressedResponse = compress(jsonResponse);

        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONNECTION, "keep-alive");
        headers.add(HttpHeaders.CONTENT_ENCODING, "gzip");
        headers.add(HttpHeaders.CONTENT_TYPE, "application/json");
        headers.add("Date", ZonedDateTime.now().format(DateTimeFormatter.RFC_1123_DATE_TIME));
        headers.add(HttpHeaders.SERVER, "nginx/1.16.1");
        headers.add(HttpHeaders.TRANSFER_ENCODING, "chunked");
        headers.add(HttpHeaders.VARY, "Accept-Encoding");

        return new ResponseEntity<>(compressedResponse, headers, HttpStatus.OK);
    }

    private byte[] compress(String data) throws IOException {
        ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
        try (GZIPOutputStream gzipStream = new GZIPOutputStream(byteStream)) {
            gzipStream.write(data.getBytes());
        }
        return byteStream.toByteArray();
    }
}
