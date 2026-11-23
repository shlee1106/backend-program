package me.shlee.springdeveloper.controller;

import lombok.Getter;
import lombok.Setter;
import me.shlee.springdeveloper.dao.Article;
import me.shlee.springdeveloper.repository.BlogRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/thymeleaf")
public class ExampleController {
    private final BlogRepository blogRepository;

    public ExampleController(BlogRepository blogRepository) {
        this.blogRepository = blogRepository;
    }

    @GetMapping("/example")
    @ResponseBody
    public String thymeleaf() {
        return "example";
    }

    @GetMapping("/example2")
    public String thymeleaf2(Model model) {
        Person person = new Person();
        person.setAge(27);
        person.setName("이승희");
        person.setBlogId(1L);
        person.setHobbies(List.of("운동", "독서", "영화감상", "음악감상"));
        model.addAttribute("person", person);
        model.addAttribute("date", LocalDate.now());

        return "example";
    }

    @Getter
    @Setter
    class Person {
        private String name;
        private int age;
        private List<String> hobbies;
        private Long blogId;
    }
}
