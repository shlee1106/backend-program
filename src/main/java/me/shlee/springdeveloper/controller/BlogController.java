package me.shlee.springdeveloper.controller;

import jakarta.websocket.server.PathParam;
import lombok.RequiredArgsConstructor;
import me.shlee.springdeveloper.dao.Article;
import me.shlee.springdeveloper.dto.ArticleCreationRequest;
import me.shlee.springdeveloper.dto.ArticleDeleteDTO;
import me.shlee.springdeveloper.dto.ArticleResponse;
import me.shlee.springdeveloper.dto.ArticleSponseDTO;
import me.shlee.springdeveloper.service.BlogService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class BlogController {
    private final BlogService blogService;



    @PostMapping("/articles")
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
     //1번 방식
    @GetMapping("/articles")
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
                .stream().map(ArticleResponse::from).toList();
        return ResponseEntity.ok().body(articleResponseList);
    }

    // 2번 방식
    @GetMapping("/articless")
    public ResponseEntity<List<ArticleSponseDTO>> getAllArticlesList() {
        return ResponseEntity.ok().body(blogService.findList());
    }

    @GetMapping("/articles/{target}")
    public ResponseEntity<ArticleResponse> getById(@PathVariable(name = "target") Long id) {
        return ResponseEntity.ok().body(blogService.getById(id));
    }

    @DeleteMapping("/articles/{target}")
    public ResponseEntity<ArticleDeleteDTO> deleteById(@PathVariable(name="target") Long id) {

        return ResponseEntity.status(HttpStatus.OK).body(blogService.deleteById(id));
    }

    @PatchMapping("/articles/{target}")
    public ResponseEntity<ArticleCreationRequest> updateArticle(@PathVariable(name = "target") Long id, @RequestBody ArticleResponse response) {
        return ResponseEntity.status(HttpStatus.OK).body(blogService.updateArticle(id, response));
    }
}
