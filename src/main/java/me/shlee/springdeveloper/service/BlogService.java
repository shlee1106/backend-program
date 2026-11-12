package me.shlee.springdeveloper.service;


import lombok.RequiredArgsConstructor;
import me.shlee.springdeveloper.dao.Article;
import me.shlee.springdeveloper.dto.*;
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
    @Transactional(readOnly = true)
    public List<ArticleSponseDTO> findList() {
        List<Article> articles = blogRepository.findAll();
        return articles.stream()
                .map(ArticleSponseDTO::from)
                .toList();
    }

    @Transactional
    public ArticleResponse getById(Long id) {
        Article article = blogRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글 없음"));

        return new ArticleResponse(article);
    }

    @Transactional
    public ArticleDeleteDTO deleteById(Long id) {
        blogRepository.deleteById(id);
        return new ArticleDeleteDTO(id, "삭제되었습니다.");
    }

    @Transactional
    public ArticleCreationRequest updateArticle(Long id, ArticleResponse articleResponse) {
        Article article = blogRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("ID를 찾을 수 없습니다."));

        if ((articleResponse.getTitle() == null) && (articleResponse.getContent() == null)) {
            throw new IllegalArgumentException("title 혹은 content 중 하나 이상은 있어야 합니다.");
        } else {
            if (articleResponse.getTitle() != null) {
                article.setTitle(articleResponse.getTitle());
            }

            if (articleResponse.getContent() != null) {
                article.setContent(articleResponse.getContent());
            }
        }

        return ArticleCreationRequest.builder()
                .title(article.getTitle())
                .content(article.getContent())
                .build();
    }
}
