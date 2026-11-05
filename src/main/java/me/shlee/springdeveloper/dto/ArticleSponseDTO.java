package me.shlee.springdeveloper.dto;


import me.shlee.springdeveloper.dao.Article;

public record ArticleSponseDTO(
        String title,
        String content
) {
    public static ArticleSponseDTO from(Article article) {
        return new ArticleSponseDTO(
                article.getTitle(),
                article.getTitle()
        );
    }
}
