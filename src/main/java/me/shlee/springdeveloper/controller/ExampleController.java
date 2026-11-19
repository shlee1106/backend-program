package me.shlee.springdeveloper.controller;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/thymeleaf")
public class ExampleController {
    @GetMapping("/example")
    @ResponseBody
    public String thymeleaf() {
        return "example";
    }

    @GetMapping("/example2")
    public String thymeleaf2(Model model) {
        Person person = new Person();
        person.setAge(25);
        person.setName("이승희");
        model.addAttribute("person", person);
        return "example";
    }

    @Getter
    @Setter
    class Person {
        private String name;
        private int age;
    }

}
