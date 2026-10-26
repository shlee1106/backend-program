package me.shlee.springdeveloper;

import jakarta.persistence.*;
import lombok.*;


@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false)
    private Long id;

    @Setter(AccessLevel.PUBLIC)
    @Column(name = "name", nullable = false)
    private String name;

    public Member(String name) {
        this.name = name;
    }
}
