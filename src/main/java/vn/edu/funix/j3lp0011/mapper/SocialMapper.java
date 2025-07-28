package vn.edu.funix.j3lp0011.mapper;

import org.mapstruct.Mapper;
import vn.edu.funix.j3lp0011.dto.SocialDto;
import vn.edu.funix.j3lp0011.entity.Social;

import java.util.List;

@Mapper(componentModel = "spring")
public interface SocialMapper {
    List<SocialDto> toDtoList(List<Social> socials);
}