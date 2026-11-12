package me.shlee.springdeveloper.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import me.shlee.springdeveloper.dao.Article;




@Getter
@AllArgsConstructor
public class ArticleResponse {

    private final String title;
    private final String content;


    public static ArticleResponse from(Article article) {
        return new ArticleResponse(
                article.getTitle(),
                article.getContent()
        );
    }
}
