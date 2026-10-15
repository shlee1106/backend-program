package me.shlee.springdeveloper;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {
    Optional<Member> findByName(String name);
    Optional<Member> findByIdAndName(Long id, String name);



    // findAll() : select * from member;
    // findById() : select * from member where id = ?;
    // deleteAll() : delete from member;
    // deleteById() : delete from member where id = ?
    // save() : insert into member(id, name) values(?,?), update member set ... where id = ?

    // findByName() : select * from member where name = ?
    // select * from member where email = ?
    // select * from member where name = ? and email = ?

    // findByEmail() : select * from member where email = ?
    // Option<Member> findByEmail(String email);

    //findByNameAndEmail() : select * from member where name = ? and email = ?
    // Option<Member> findByNameAndEmail(String name, String email);
}
