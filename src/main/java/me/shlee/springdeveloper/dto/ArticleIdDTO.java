package me.shlee.springdeveloper.dto;

import lombok.Getter;
import me.shlee.springdeveloper.dao.Article;

@Getter
public class ArticleIdDTO {

    private final Long id;

    public ArticleIdDTO(Article article) {
        this.id = article.getId();
    }
}
