package me.shlee.springdeveloper;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
public class HelloWorldController {

    // "/hello" 요청을 보내면 hello() 메서드 호출
    // http://localhost:8080/hello
    @GetMapping("/hello")
    public String hello() {

        return "Controller Hello";
    }

//    @GetMapping("/test")
//    // @ResponseBody
//    public String test() {
//
//        return "Hello Everyone!!!!!!!";
//    }



//    @PostMapping("/test")
//    public String postTest() {
//        return "Post Test response!!!!!";
//    }
//
//    @DeleteMapping("/test")
//    public String deleteTest() {
//        return "Delete test response!!!!";
//    }
//
//    @PutMapping("/test")
//    public String putTest() {
//        return "Put test response!!!!";
//    }
//
//    @PatchMapping("/test")
//    public String patchTest() {
//        return "Patch test response!!!!";
//    }
}
