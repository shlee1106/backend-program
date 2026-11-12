package me.shlee.springdeveloper.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import me.shlee.springdeveloper.dao.Article;

import java.util.Optional;


@Getter
@RequiredArgsConstructor
public class ArticleResponse {

    private final String title;
    private final String content;

    public ArticleResponse(Article article) {
        this.title = article.getTitle();
        this.content = article.getContent();
    }

    public static ArticleResponse from(Article article) {
        return new ArticleResponse(
                article.getTitle(),
                article.getContent()
        );
    }
}
