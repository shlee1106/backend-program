package me.shlee.springdeveloper.controller;

import lombok.RequiredArgsConstructor;
import me.shlee.springdeveloper.dao.Article;
import me.shlee.springdeveloper.dto.ArticleCreationRequest;
import me.shlee.springdeveloper.service.BlogService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class BlogController {
    private final BlogService blogService;

    @PostMapping("/api/articles")
    public ResponseEntity<Article> addAtricle(@RequestBody ArticleCreationRequest dto) {

        // 서비스에게 블로그 글 등록을 의뢰.
        // 성공했다면 ResponseEntity 객체 반환
        Article savedArticle = blogService.save(dto);
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(savedArticle);
        }
        catch(Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

    }
}
