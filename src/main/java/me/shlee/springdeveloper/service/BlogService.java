package me.shlee.springdeveloper.service;


import lombok.RequiredArgsConstructor;
import me.shlee.springdeveloper.dao.Article;
import me.shlee.springdeveloper.dto.ArticleCreationRequest;
import me.shlee.springdeveloper.dto.ArticleSponseDTO;
import me.shlee.springdeveloper.repository.BlogRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BlogService {
    private final BlogRepository blogRepository;

    @Transactional
    public Article save(ArticleCreationRequest articleCreationRequest) {

        return blogRepository.save(articleCreationRequest.toEntity());
    }

    // 1번 방법
    @Transactional(readOnly = true)
    public List<Article> findAll() {
        return blogRepository.findAll();
    }


    // 2번 방법
    @Transactional
    public List<ArticleSponseDTO> findList() {
        List<Article> articles = blogRepository.findAll();
        return articles.stream()
                .map(ArticleSponseDTO::from)
                .toList();
    }
}
