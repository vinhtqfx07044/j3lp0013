package vn.edu.funix.j3lp0011.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import vn.edu.funix.j3lp0011.dto.PostDto;
import vn.edu.funix.j3lp0011.entity.Post;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PostMapper {

    @Mapping(target = "quoteContent", source = "content", qualifiedByName = "extractQuoteContent")
    @Mapping(target = "quoteAuthor", source = "content", qualifiedByName = "extractQuoteAuthor")
    PostDto toDto(Post post);

    List<PostDto> toDtoList(List<Post> posts);

    @Named("extractQuoteContent")
    default String extractQuoteContent(String content) {
        if (content == null || !content.contains("-")) {
            return content;
        }
        return content.substring(0, content.lastIndexOf("-")).trim();
    }

    @Named("extractQuoteAuthor")
    default String extractQuoteAuthor(String content) {
        if (content == null || !content.contains("-")) {
            return null;
        }
        return content.substring(content.lastIndexOf("-") + 1).trim();
    }
}