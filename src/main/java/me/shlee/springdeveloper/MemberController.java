package me.shlee.springdeveloper;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RequiredArgsConstructor
@RestController
public class MemberController {
    // 요청을 받아서 적절한 비즈니스로직으로 연결
    // http://localhost:8080/member 요청과 메서드를 연결

    private final MemberService memberService;

    @GetMapping("/member")
    public List<Member> getAllMembers() {
        List<Member> members = memberService.getAllMembers();
        return members;
    }
}
