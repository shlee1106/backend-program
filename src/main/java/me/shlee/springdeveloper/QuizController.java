package me.shlee.springdeveloper;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
public class QuizController {
    // 요청 URL과 요청 방식(GET, POST, PATCH, PUT, DELETE)에 따라 그 요청을 실행할 메소드를 매핑

    @GetMapping("/quiz")
    public ResponseEntity<String> quiz(@RequestParam(value = "code", required = true) int code,
                                       @RequestParam(value = "name", required = true) String name,
                                       @RequestParam(value = "age", required = false, defaultValue = "20") String age) {

        switch (code) {
            case 1:
                return ResponseEntity.created(null).body("Created!"); // 201, "Created!"
            case 2:
                return ResponseEntity.badRequest().body("Bad Request!"); // 400, "Bad Request!"
            default:
                return ResponseEntity.ok("OK"); // 200, "Ok"
        }

    }

    @PostMapping("/quiz")
    public ResponseEntity<String> quiz2(@RequestBody Code code) {

        switch (code.value()) {
            case 1:
                return ResponseEntity.status(403).body("Created!");
            default:
                return ResponseEntity.ok("OK!");
        }


    }
}
