package kz.warstation.api.controller;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.zip.GZIPOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

@RestController
@RequestMapping("/warstation/accounts")
public class ProfileController {

    @GetMapping("/my")
    public ResponseEntity<String> getAccountInfo() {
        String jsonResponse = "{\"id\":1115,\"uuid\":\"e27c682a-975b-4015-a0d0-9edf5a6f4175\",\"phone\":\"\",\"city\":\"Тараз\",\"address\":\"\",\"name\":\"Руслан\",\"site\":\"\",\"franchise_tarif\":[{\"id\":34,\"name\":\"Battlestart_16\",\"is_outdated\":false,\"franchise\":{\"id\":2,\"name\":\"AvatarGames\"},\"subscription_end_date\":null,\"auto_renewal\":false}],\"has_subscription\":false,\"subscription_payment\":15000,\"subscription_end_date\":null,\"per_minute_billing\":false,\"total_minutes_paid\":0,\"total_minutes_spent\":0,\"seconds_correction\":0,\"minutes_counted_until\":\"1970-01-01T00:00:00Z\",\"last_minutes_top_up_at\":\"1970-01-01T00:00:00Z\",\"owner\":{\"id\":\"4cdb427f-8377-4d1c-8baf-430a7325a25b\",\"email\":\"taraztest@mail.ru\",\"is_active\":true,\"is_superuser\":false,\"is_verified\":false,\"permissions\":[]},\"manager\":{\"id\":1,\"name\":\"Никита Афонычев\",\"phone\":\"+7 (950) 701-22-11\",\"email\":\"v2b@warstation.com, Info@avatargames.ru\",\"created_at\":\"2023-09-15T08:21:50.788441Z\",\"updated_at\":\"2023-09-15T08:21:50.788472Z\"},\"minutes_balance\":0}";

        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONNECTION, "keep-alive");
        headers.add(HttpHeaders.CONTENT_TYPE, "application/json");
        headers.add(HttpHeaders.SERVER, "nginx/1.20.2");
        headers.add(HttpHeaders.TRANSFER_ENCODING, "chunked");
        headers.add(HttpHeaders.VARY, "Accept-Encoding");
        headers.add("date", "Sun, 05 Oct 2025 16:52:02 GMT");
        headers.add("x-sign", "MEUCIH4R0cg9VvJTAKSKDTuD9R9kliA+Xjj9I1ro05LmHhtrAiEA/USFfhgnQRZxml+oQMSQg1jJkX8jEdpB8VzAId1eshI=");

        return new ResponseEntity<>(jsonResponse, headers, HttpStatus.OK);
    }

    private byte[] compress(String data) throws IOException {
        ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
        try (GZIPOutputStream gzipStream = new GZIPOutputStream(byteStream)) {
            gzipStream.write(data.getBytes());
        }
        return byteStream.toByteArray();
    }
}
