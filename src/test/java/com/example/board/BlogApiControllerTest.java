package com.example.board;

import com.example.board.dao.Article;
import com.example.board.dto.ArticleRequest;
import com.example.board.repository.BlogRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import tools.jackson.databind.ObjectMapper;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class BlogApiControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private BlogRepository blogRepository;

    @DisplayName("게시글 등록에 성공한다")

    @AfterEach
    public void cleanup() {
        blogRepository.deleteAll();
    }

    @Test
    @DisplayName("게시글 등록 성공한다.")
    public void addArticle() throws Exception{
        // 준비 (given)
        final String url = "/api/articles";
        final String title = "게시글 등록 테스트";
        final String content = "게시글 등록 성공";
        ArticleRequest req = new ArticleRequest(title, content);
        // 실행 (when)
        ResultActions resultActions = mockMvc.perform(post(url, req)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(req)));

        // 검증 (then)

        // 1. 상태 응답 코드
        resultActions.andExpect(status().isCreated());

        // 2. findAll 결과값이 1개인지 확인(등록 되었는지 확인)
        List<Article> articles = blogRepository.findAll();
        assertThat(articles.size()).isEqualTo(1);


        // 3. DB에 저장된 제목, 본문 내용이 요청한 값과 같은지 확인
        assertThat(articles.get(0).getTitle()).isEqualTo(title);
        assertThat(articles.get(0).getContent()).isEqualTo(content);
    }

    @Test
    @DisplayName("특정 게시글 조회 성공한다.")
    public void findArticle() throws Exception{

        // 준비 (given)
        final String url = "/api/articles/{id}";
        final String title = "특정 게시글 조회";
        final String content = "특정 게시글 조회 성공";
        ArticleRequest req = new ArticleRequest(title, content);
        Article article = blogRepository.save(req.toEntity()); // DB 저장
        Long id = article.getId();

        // 실행 (when)
        ResultActions resultActions = mockMvc.perform(get(url, id)
                .accept(MediaType.APPLICATION_JSON));

        // 검증 (then)
        resultActions
                .andExpect(status().isOk()) // 상태 응답 코드
                .andExpect(jsonPath("$.title").value(title)) // title 검증
                .andExpect(jsonPath("$.content").value(content)); // content 검증
    }

    @DisplayName("특정 게시글 수정 성공한다")
    @Test
    public void updateArticle() throws Exception{

        // 준비 (given)
        final String url = "/api/articles/{id}";
        final String title = "수정 전 타이틀";
        final String content = "수정 전 본문";
        ArticleRequest req = new ArticleRequest(title, content);
        Article article = blogRepository.save(req.toEntity());
        Long id = article.getId();

        final String newTitle = "수정 된 타이틀!";
        final String newContent = "수정 된 본문!";

        ArticleRequest newReq = new ArticleRequest(newTitle, newContent);

        // 실행 (when)
        ResultActions result = mockMvc.perform(patch(url, id)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(newReq)));

        // 검증 (then)

        // 그전 Article을 들고 검증 할 시 이전에 등록된 title, content와 검증하게 되므로 실행 단계를 거친 후 새로운 Article 객체 생성
        Article updateArticle = blogRepository.findById(id).get();

        result.andExpect(status().isOk()); // 상태코드 검증
        assertThat(updateArticle.getTitle()).isEqualTo(newTitle); // DB에 새로 등록된 title 검증
        assertThat(updateArticle.getContent()).isEqualTo(newContent); // DB에 새로 등록된 content 검증

    }

    @DisplayName("특정 게시글 삭제 성공한다.")
    @Test
    public void deleteArticle() throws Exception{

        // 준비 (given)
        final String url = "/api/articles/{id}";
        final String title = "특정 게시글 삭제";
        final String content = "특정 게시글 삭제 성공";
        ArticleRequest req = new ArticleRequest(title, content);
        Article article = blogRepository.save(req.toEntity());
        Long id = article.getId();

        // 실행 (when)
        ResultActions result = mockMvc.perform(delete(url, id));

        // 검증 (then)

        result.andExpect(status().isOk()); // 상태 코드 검증

        // DB에 삭제된 article ID 조회 후 들고 온뒤 검증
        assertThat(blogRepository.findById(id)).isEmpty();
    }

    @DisplayName("모든 게시글 조회에 성공한다.")
    @Test
    public void findAllArticles() throws Exception{

        // 준비 (given)
        final String url = "/api/articles";
        final String title1 = "첫번째 게시글";
        final String content1 = "첫번째 본문내용";

        final String title2 = "두번째 게시글";
        final String content2 = "두번째 본문내용";

        ArticleRequest req1 = new ArticleRequest(title1, content1);
        ArticleRequest req2 = new ArticleRequest(title2, content2);

        blogRepository.save(req1.toEntity());
        blogRepository.save(req2.toEntity());

        List<Article> articles = blogRepository.findAll();
        // 실행 (when)
        ResultActions result = mockMvc.perform(get(url)
                .accept(MediaType.APPLICATION_JSON));

        // 검증 (then)
        result.andExpect(status().isOk()) // 상태 코드 검증

        // 등록된 게시글 2개인지 검증
                .andExpect(jsonPath("$.length()").value(2))

        // 각 글의 title, content 일치 한지 검증
                .andExpect(jsonPath("$[0].title").value(title1))
                .andExpect(jsonPath("$[0].content").value(content1))
                .andExpect(jsonPath("$[1].title").value(title2))
                .andExpect(jsonPath("$[1].content").value(content2));
    }
}
