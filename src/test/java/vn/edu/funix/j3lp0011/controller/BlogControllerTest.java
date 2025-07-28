package vn.edu.funix.j3lp0011.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import vn.edu.funix.j3lp0011.dto.PostDto;
import vn.edu.funix.j3lp0011.exception.PostNotFoundException;
import vn.edu.funix.j3lp0011.service.BlogService;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(controllers = {BlogController.class, AboutMeController.class})
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class BlogControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BlogService blogService;

    @Test
    void showHomePage_shouldReturnHomePageWithPosts() throws Exception {
        // Arrange
        PostDto post1 = new PostDto();
        post1.setId(1);
        post1.setTitle("First Post");
        post1.setType("blog");
        post1.setContent("First post content");
        post1.setImagePath("i1.jpg");
        post1.setCreatedAt(LocalDate.now());
        PostDto post2 = new PostDto();
        post2.setId(2);
        post2.setTitle("Second Post");
        post2.setType("blog");
        post2.setContent("Second post content");
        post2.setImagePath("i2.jpg");
        post2.setCreatedAt(LocalDate.now());
        when(blogService.getHomepagePosts()).thenReturn(List.of(post1, post2));
        when(blogService.getSocials()).thenReturn(List.of());

        // Act & Assert
        mockMvc.perform(get("/home"))
                .andExpect(status().isOk())
                .andExpect(view().name("home"))
                .andExpect(model().attributeExists("posts"))
                .andExpect(model().attribute("posts", hasSize(2)))
                .andExpect(model().attribute("posts", hasItem(
                        hasProperty("title", is("First Post"))
                )));
    }

    @Test
    void showPostDetailPage_whenPostExists_shouldReturnPostDetailView() throws Exception {
        // Arrange
        PostDto post = new PostDto();
        post.setId(1);
        post.setTitle("Test Post");
        post.setType("blog");
        post.setContent("Test content");
        post.setImagePath("i1.jpg");
        post.setCreatedAt(LocalDate.now());
        when(blogService.getPostById(1)).thenReturn(post);
        when(blogService.getSocials()).thenReturn(List.of());

        // Act & Assert
        mockMvc.perform(get("/post/1"))
                .andExpect(status().isOk())
                .andExpect(view().name("post-detail"))
                .andExpect(model().attribute("post", hasProperty("id", is(1))));
    }

    @Test
    void showPostDetailPage_whenPostDoesNotExist_shouldReturnNotFoundStatus() throws Exception {
        // Arrange
        when(blogService.getPostById(99)).thenThrow(new PostNotFoundException("Post not found"));

        // Act & Assert
        mockMvc.perform(get("/post/99"))
                .andExpect(status().isNotFound());
    }

    @Test
    void showOverviewPage_shouldReturnOverviewViewWithGroupedPosts() throws Exception {
        // Arrange
        PostDto post1 = new PostDto();
        post1.setId(1);
        post1.setTitle("May Post");
        post1.setType("blog");
        post1.setContent("May post content");
        post1.setImagePath("i1.jpg");
        post1.setCreatedAt(LocalDate.now());
        Map<String, List<PostDto>> groupedData = Map.of("MAY 2024", List.of(post1));
        when(blogService.getGroupedPosts()).thenReturn(groupedData);
        when(blogService.getSocials()).thenReturn(List.of());

        // Act & Assert
        mockMvc.perform(get("/overview"))
                .andExpect(status().isOk())
                .andExpect(view().name("overview"))
                .andExpect(model().attributeExists("groupedPosts"))
                .andExpect(model().attribute("groupedPosts", hasKey("MAY 2024")));
    }
}