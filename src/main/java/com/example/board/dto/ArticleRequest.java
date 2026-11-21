package com.example.board.dto;

import com.example.board.dao.Article;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ArticleRequest {
    private String title;
    private String content;

    public Article toEntity() {
       return Article.builder()
               .title(title)
               .content(content)
               .build();
    }
}
