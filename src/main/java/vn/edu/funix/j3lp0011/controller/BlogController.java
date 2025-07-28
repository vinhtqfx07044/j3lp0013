package vn.edu.funix.j3lp0011.controller;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import vn.edu.funix.j3lp0011.dto.PostDto;

import java.util.List;
import vn.edu.funix.j3lp0011.service.BlogService;

@Controller
@RequiredArgsConstructor
@RequestMapping("/")
public class BlogController {

    private final BlogService blogService;

    private void addCommonAttributes(Model model) {
        model.addAttribute("socials", blogService.getSocials());
    }

    @GetMapping({ "/", "/home" })
    public String showHomePage(Model model, HttpSession session) {
        // Force session creation to trigger SessionListener
        session.setAttribute("visited", true);
        model.addAttribute("posts", blogService.getHomepagePosts());
        addCommonAttributes(model);
        return "home";
    }

    @GetMapping("/post/{id}")
    public String showPostDetailPage(@PathVariable int id, Model model) {
        model.addAttribute("post", blogService.getPostById(id));
        addCommonAttributes(model);
        return "post-detail";
    }

    @GetMapping("/overview")
    public String showOverviewPage(Model model) {
        model.addAttribute("groupedPosts", blogService.getGroupedPosts());
        addCommonAttributes(model);
        return "overview";
    }

    @GetMapping("/api/posts")
    @ResponseBody
    public List<PostDto> getPostsApi() {
        return blogService.getHomepagePosts();
    }
}