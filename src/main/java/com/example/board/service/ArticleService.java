package com.example.board.service;

import com.example.board.dao.Article;
import com.example.board.dto.ArticleDelete;
import com.example.board.dto.ArticleRequest;
import com.example.board.dto.ArticleResponse;
import com.example.board.repository.BlogRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ArticleService {

    private final BlogRepository blogRepository;

    @Transactional
    public ArticleResponse createArticle(ArticleRequest articleRequest) {
        Article article = blogRepository.save(articleRequest.toEntity());
        return ArticleResponse.from(article);
    }

    public List<ArticleResponse> findAllArticle() {
        List<Article> articleList = blogRepository.findAll();
        return ArticleResponse.from(articleList);
    }

    public ArticleResponse findByIdArticle(Long id) {
        Article article = getArticleOrThrow(id);

        return ArticleResponse.from(article);
    }

    @Transactional
    public ArticleDelete deleteByArticle(Long id) {
        Article article = getArticleOrThrow(id);
        blogRepository.delete(article);
        return new ArticleDelete(id, "삭제 되었습니다.");
    }

    @Transactional
    public ArticleResponse updateArticle(Long id, ArticleRequest articleRequest) {
        Article article = getArticleOrThrow(id);
        if(articleRequest.getTitle() != null) {
            article.setTitle(articleRequest.getTitle());
        }
        if(articleRequest.getContent() != null) {
            article.setContent(articleRequest.getContent());
        }
        return ArticleResponse.from(article);
    }

    private Article getArticleOrThrow(Long id) {
        return blogRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("존재 하지 않는 게시글아이디입니다."));
    }
}
