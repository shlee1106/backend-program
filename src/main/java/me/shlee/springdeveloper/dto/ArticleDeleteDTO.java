package me.shlee.springdeveloper.dto;

public record ArticleDeleteDTO(Long id, String message) {

    public ArticleDeleteDTO(Long id, String message) {
        this.id = id;
        this.message = message;
    }
}
