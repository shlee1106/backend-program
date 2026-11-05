package me.shlee.springdeveloper.controller;

import lombok.RequiredArgsConstructor;
import me.shlee.springdeveloper.dao.Article;
import me.shlee.springdeveloper.dto.ArticleCreationRequest;
import me.shlee.springdeveloper.dto.ArticleResponse;
import me.shlee.springdeveloper.dto.ArticleSponseDTO;
import me.shlee.springdeveloper.service.BlogService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class BlogController {
    private final BlogService blogService;

    @PostMapping("/api/articles")
    public ResponseEntity<Article> addArticle(@RequestBody ArticleCreationRequest dto) {

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
    // 1번 방식
    @GetMapping("/api/articles")
    public ResponseEntity<List<ArticleResponse>> getAllArticles() {
        /*
        List<Article> articleList = blogService.findAll();
        List<ArticleResponse> articleResponseList = new ArrayList<>();
        for (Article article : articleList) {
            articleResponseList.add(new ArticleResponse(article));
        }
        return ResponseEntity.status(HttpStatus.OK).body(articleResponseList);
         */
        List<ArticleResponse> articleResponseList = blogService.findAll()
                .stream().map(ArticleResponse::new).toList();
        return ResponseEntity.ok().body(articleResponseList);
    }

    // 2번 방식
    @GetMapping("/api/articless")
    public ResponseEntity<List<ArticleSponseDTO>> getAllArticlesList() {
        return ResponseEntity.ok().body(blogService.findList());
    }
}
