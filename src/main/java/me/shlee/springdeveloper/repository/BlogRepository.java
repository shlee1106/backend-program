package me.shlee.springdeveloper.repository;

import me.shlee.springdeveloper.dao.Article;
import me.shlee.springdeveloper.dto.ArticleIdDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BlogRepository extends JpaRepository<Article, Long> {
    // findById(), findAll(), save(), saveAll(), deleteById(), deleteAll()
//    Optional<Article> findById (Long Id);

}
