package com.example.board.dto;

import com.example.board.dao.Article;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class ArticleResponse {
    private String title;
    private String content;

    public static ArticleResponse from (Article article) {
        return new ArticleResponse(
                article.getTitle(),
                article.getContent()
        );
    }

    public static List<ArticleResponse> from (List<Article> articleList) {
        return articleList.stream()
                .map(ArticleResponse::from)
                .toList();
    }
}
