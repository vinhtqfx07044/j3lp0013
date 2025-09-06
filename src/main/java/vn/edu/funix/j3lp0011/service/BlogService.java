package vn.edu.funix.j3lp0011.service;

import vn.edu.funix.j3lp0011.dto.PostDetailDTO;
import vn.edu.funix.j3lp0011.dto.PostOverviewDTO;
import vn.edu.funix.j3lp0011.dto.PostHomeDTO;
import vn.edu.funix.j3lp0011.dto.SocialDTO;
import vn.edu.funix.j3lp0011.entity.AboutMe;

import java.util.List;
import java.util.Map;

public interface BlogService {

    List<PostHomeDTO> getHomepagePosts();

    PostDetailDTO getPostById(int id);

    Map<String, List<PostOverviewDTO>> getGroupedPosts();

    AboutMe getAboutMe();

    List<SocialDTO> getSocials();
}