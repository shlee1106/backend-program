package me.shlee.springdeveloper;

import java.util.List;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class MemberService {


    private final MemberRepository memberRepository;

    public List<Member> getAllMembers() {

        return memberRepository.findAll(); //select * from member SQL문
    }
}
