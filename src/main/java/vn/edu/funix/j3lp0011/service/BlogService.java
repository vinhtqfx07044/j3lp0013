package vn.edu.funix.j3lp0011.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import vn.edu.funix.j3lp0011.dto.PostDetailDTO;
import vn.edu.funix.j3lp0011.dto.PostSummaryDTO;
import vn.edu.funix.j3lp0011.dto.SocialDTO;
import vn.edu.funix.j3lp0011.entity.AboutMe;
import vn.edu.funix.j3lp0011.entity.Post;
import vn.edu.funix.j3lp0011.entity.Social;
import vn.edu.funix.j3lp0011.exception.ResourceNotFoundException;
import vn.edu.funix.j3lp0011.repository.AboutMeRepository;
import vn.edu.funix.j3lp0011.repository.PostRepository;
import vn.edu.funix.j3lp0011.repository.SocialRepository;

import java.time.format.TextStyle;
import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BlogService {

    private final PostRepository postRepository;
    private final AboutMeRepository aboutMeRepository;
    private final SocialRepository socialRepository;

    @Value("${blog.posts.homepage-count:3}")
    private int homepageCount;

    private PostSummaryDTO mapToPostSummaryDTO(Post post) {
        PostSummaryDTO dto = new PostSummaryDTO();
        dto.setId(post.getId());
        dto.setTitle(post.getTitle());
        dto.setType(post.getType());
        dto.setContent(post.getContent());
        dto.setImagePath(post.getImagePath());
        dto.setCreatedAt(post.getCreatedAt());
        dto.setQuoteText(post.getQuoteText());
        dto.setQuoteAuthor(post.getQuoteAuthor());
        return dto;
    }

    private PostDetailDTO mapToPostDetailDTO(Post post) {
        PostDetailDTO dto = new PostDetailDTO();
        dto.setId(post.getId());
        dto.setTitle(post.getTitle());
        dto.setType(post.getType());
        dto.setContent(post.getContent());
        dto.setImagePath(post.getImagePath());
        dto.setCreatedAt(post.getCreatedAt());
        dto.setQuoteText(post.getQuoteText());
        dto.setQuoteAuthor(post.getQuoteAuthor());
        return dto;
    }

    private SocialDTO mapToSocialDTO(Social social) {
        SocialDTO dto = new SocialDTO();
        dto.setId(social.getId());
        dto.setName(social.getName());
        dto.setIcon(social.getIcon());
        return dto;
    }

    public List<PostSummaryDTO> getHomepagePosts() {
        var pageable = PageRequest.of(0, homepageCount);
        return postRepository.findByOrderByCreatedAtDesc(pageable)
                .stream()
                .map(this::mapToPostSummaryDTO)
                .collect(Collectors.toList());
    }

    public PostDetailDTO getPostById(int id) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Post not found with id: " + id));
        return mapToPostDetailDTO(post);
    }

    public Map<String, List<PostSummaryDTO>> getGroupedPosts() {
        List<Post> posts = postRepository.findAll(Sort.by(Sort.Direction.DESC, "createdAt"));

        return posts.stream()
                .map(this::mapToPostSummaryDTO)
                .collect(Collectors.groupingBy(
                        post -> post.getCreatedAt().getMonth().getDisplayName(TextStyle.FULL, Locale.ENGLISH)
                                .toUpperCase()
                                + " " + post.getCreatedAt().getYear(),
                        LinkedHashMap::new,
                        Collectors.toList()));
    }

    public AboutMe getAboutMe() {
        return aboutMeRepository.findById(1)
                .orElse(new AboutMe());
    }

    public List<SocialDTO> getSocials() {
        return socialRepository.findAll()
                .stream()
                .map(this::mapToSocialDTO)
                .collect(Collectors.toList());
    }
}
