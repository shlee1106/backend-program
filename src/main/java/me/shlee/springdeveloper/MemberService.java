package me.shlee.springdeveloper;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class MemberService {


    private final MemberRepository memberRepository;

    public List<Member> getAllMembers() {

        return memberRepository.findAll(); //select * from member SQL문
    }

    public void setMembers(MemberDTO memberDTO) {
        Member user = new Member();
        user.setName(memberDTO.name());
        memberRepository.save(user);
    }

    public MemberDTO findUser(String name) {
         Member user = memberRepository.findByName(name)
                 .orElseThrow(() -> new IllegalArgumentException("사용자 찾을 수 없음"));

         return new MemberDTO(
                 user.getName()
         );
    }
}
