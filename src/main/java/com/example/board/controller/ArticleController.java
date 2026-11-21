package com.example.board.controller;

import com.example.board.dto.ArticleDelete;
import com.example.board.dto.ArticleRequest;
import com.example.board.dto.ArticleResponse;
import com.example.board.service.ArticleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class ArticleController {

    private final ArticleService service;

    @PostMapping("/articles")
    public ResponseEntity<ArticleResponse> createArticle(@RequestBody ArticleRequest articleRequest) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.createArticle(articleRequest));
    }

    @GetMapping("/articles")
    public ResponseEntity<List<ArticleResponse>> findAllArticle() {
        return ResponseEntity.status(HttpStatus.OK).body(service.findAllArticle());
    }

    @GetMapping("/articles/{target}")
    public ResponseEntity<ArticleResponse> findByIdArticle(@PathVariable(name = "target") Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(service.findByIdArticle(id));
    }

    @DeleteMapping("/articles/{target}")
    public ResponseEntity<ArticleDelete> deleteByArticle(@PathVariable(name = "target") Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(service.deleteByArticle(id));
    }

    @PatchMapping("/articles/{target}")
    public ResponseEntity<ArticleResponse> updateArticle(@PathVariable(name = "target") Long id,
                                                         @RequestBody ArticleRequest article) {
        return ResponseEntity.status(HttpStatus.OK).body(service.updateArticle(id, article));
    }
}
