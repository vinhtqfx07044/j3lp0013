package vn.edu.funix.j3lp0011.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Sort;
import vn.edu.funix.j3lp0011.dto.AboutMeDto;
import vn.edu.funix.j3lp0011.dto.PostDto;
import vn.edu.funix.j3lp0011.entity.AboutMe;
import vn.edu.funix.j3lp0011.entity.Post;
import vn.edu.funix.j3lp0011.exception.PostNotFoundException;
import vn.edu.funix.j3lp0011.mapper.AboutMeMapper;
import vn.edu.funix.j3lp0011.mapper.PostMapper;
import vn.edu.funix.j3lp0011.mapper.SocialMapper;
import vn.edu.funix.j3lp0011.repository.AboutMeRepository;
import vn.edu.funix.j3lp0011.repository.PostRepository;
import vn.edu.funix.j3lp0011.repository.SocialRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class BlogServiceTest {

    @Mock
    private PostRepository postRepository;
    @Mock
    private AboutMeRepository aboutMeRepository;
    @Mock
    private SocialRepository socialRepository;
    @Mock
    private PostMapper postMapper;
    @Mock
    private AboutMeMapper aboutMeMapper;
    @Mock
    private SocialMapper socialMapper;

    @InjectMocks
    private BlogService blogService;

    private Post post1;
    private PostDto postDto1;

    @BeforeEach
    void setUp() {
        // Khởi tạo dữ liệu mẫu để tái sử dụng trong các test
        post1 = new Post();
        post1.setId(1);
        post1.setTitle("Test Post 1");
        post1.setCreatedAt(LocalDate.of(2024, 5, 20));

        postDto1 = new PostDto();
        postDto1.setId(1);
        postDto1.setTitle("Test Post 1");
    }

    @Test
    void getPostById_whenPostExists_shouldReturnPostDto() {
        // Arrange
        when(postRepository.findById(1)).thenReturn(Optional.of(post1));
        when(postMapper.toDto(post1)).thenReturn(postDto1);

        // Act
        PostDto foundPost = blogService.getPostById(1);

        // Assert
        assertNotNull(foundPost);
        assertEquals(1, foundPost.getId());
        assertEquals("Test Post 1", foundPost.getTitle());

        // Verify
        verify(postRepository, times(1)).findById(1);
        verify(postMapper, times(1)).toDto(post1);
    }

    @Test
    void getPostById_whenPostDoesNotExist_shouldThrowPostNotFoundException() {
        // Arrange
        when(postRepository.findById(99)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(PostNotFoundException.class, () -> {
            blogService.getPostById(99);
        });

        // Verify
        verify(postRepository, times(1)).findById(99);
        verify(postMapper, never()).toDto(any());
    }

    @Test
    void getGroupedPosts_shouldReturnMapOfStringAndListOfPostDto() {
        // Arrange
        Post post2 = new Post();
        post2.setId(2);
        post2.setTitle("Test Post 2");
        post2.setCreatedAt(LocalDate.of(2024, 4, 15));

        List<Post> posts = List.of(post1, post2);
        when(postRepository.findAll(any(Sort.class))).thenReturn(posts);
        when(postMapper.toDto(any(Post.class))).thenAnswer(invocation -> {
            Post p = invocation.getArgument(0);
            PostDto dto = new PostDto();
            dto.setId(p.getId());
            dto.setTitle(p.getTitle());
            return dto;
        });

        // Act
        Map<String, List<PostDto>> groupedPosts = blogService.getGroupedPosts();

        // Assert
        assertNotNull(groupedPosts);
        assertEquals(2, groupedPosts.size()); // 2 tháng khác nhau
        assertTrue(groupedPosts.containsKey("MAY 2024"));
        assertTrue(groupedPosts.containsKey("APRIL 2024"));
        assertEquals(1, groupedPosts.get("MAY 2024").size());
    }

    @Test
    void getAboutMe_whenDataExists_shouldReturnAboutMeDto() {
        // Arrange
        AboutMe aboutMeEntity = new AboutMe();
        aboutMeEntity.setId(1);
        aboutMeEntity.setAuthor("Test Author");

        AboutMeDto aboutMeDto = new AboutMeDto();
        aboutMeDto.setAuthor("Test Author");

        when(aboutMeRepository.findById(1)).thenReturn(Optional.of(aboutMeEntity));
        when(aboutMeMapper.toDto(aboutMeEntity)).thenReturn(aboutMeDto);

        // Act
        AboutMeDto result = blogService.getAboutMe();

        // Assert
        assertNotNull(result);
        assertEquals("Test Author", result.getAuthor());
    }
}