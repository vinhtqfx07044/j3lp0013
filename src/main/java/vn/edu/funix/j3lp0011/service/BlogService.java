package vn.edu.funix.j3lp0011.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import vn.edu.funix.j3lp0011.dto.AboutMeDto;
import vn.edu.funix.j3lp0011.dto.PostDto;
import vn.edu.funix.j3lp0011.dto.SocialDto;
import vn.edu.funix.j3lp0011.entity.Post;
import vn.edu.funix.j3lp0011.exception.PostNotFoundException;
import vn.edu.funix.j3lp0011.mapper.AboutMeMapper;
import vn.edu.funix.j3lp0011.mapper.PostMapper;
import vn.edu.funix.j3lp0011.mapper.SocialMapper;
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

    private final PostMapper postMapper;
    private final AboutMeMapper aboutMeMapper;
    private final SocialMapper socialMapper;

    @Value("${blog.posts.homepage-count:3}")
    private int homepageCount;

    public List<PostDto> getHomepagePosts() {
        var pageable = PageRequest.of(0, homepageCount);
        var posts = postRepository.findByOrderByCreatedAtDesc(pageable);
        return postMapper.toDtoList(posts);
    }

    public PostDto getPostById(int id) {
        return postRepository.findById(id)
                .map(postMapper::toDto)
                .orElseThrow(() -> new PostNotFoundException("Post not found with id: " + id));
    }

    public Map<String, List<PostDto>> getGroupedPosts() {
        List<Post> posts = postRepository.findAll(Sort.by(Sort.Direction.DESC, "createdAt"));

        return posts.stream()
                .collect(Collectors.groupingBy(
                        post -> post.getCreatedAt().getMonth().getDisplayName(TextStyle.FULL, Locale.ENGLISH).toUpperCase()
                                + " " + post.getCreatedAt().getYear(),
                        LinkedHashMap::new, // Giữ thứ tự
                        Collectors.mapping(postMapper::toDto, Collectors.toList())
                ));
    }

    public AboutMeDto getAboutMe() {
        return aboutMeRepository.findById(1)
                .map(aboutMeMapper::toDto)
                .orElse(new AboutMeDto()); // Trả về DTO rỗng nếu không tìm thấy
    }

    public List<SocialDto> getSocials() {
        return socialMapper.toDtoList(socialRepository.findAll());
    }
}