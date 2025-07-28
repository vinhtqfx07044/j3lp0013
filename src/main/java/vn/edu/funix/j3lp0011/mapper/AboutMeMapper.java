package vn.edu.funix.j3lp0011.mapper;

import org.mapstruct.Mapper;
import vn.edu.funix.j3lp0011.dto.AboutMeDto;
import vn.edu.funix.j3lp0011.entity.AboutMe;

@Mapper(componentModel = "spring")
public interface AboutMeMapper {
    AboutMeDto toDto(AboutMe aboutMe);
}