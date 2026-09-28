package me.shlee.springdeveloper;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.awt.*;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RestController
public class MemberController {
    // 요청을 받아서 적절한 비즈니스로직으로 연결
    // http://localhost:8080/member 요청과 메서드를 연결

    private final MemberService memberService;

    @GetMapping("/member")
    public ResponseEntity<List<Member>> getAllMembers() {
        List<Member> members = memberService.getAllMembers();
        return ResponseEntity.status(HttpStatus.OK).body(members);
    }

    @PostMapping(value = "/join")
    public ResponseEntity<String> setMembers(@RequestBody MemberDTO dto) {
        memberService.setMembers(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body("계정 생성 완료.");
    }

    @GetMapping("/user")
    public ResponseEntity<MemberDTO> findUser(@RequestParam("name") String name) {
        return ResponseEntity.status(HttpStatus.OK).body(memberService.findUser(name));
    }
}
