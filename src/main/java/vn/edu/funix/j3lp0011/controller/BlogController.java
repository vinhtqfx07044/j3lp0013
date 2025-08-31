package vn.edu.funix.j3lp0011.controller;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import vn.edu.funix.j3lp0011.service.BlogService;
import vn.edu.funix.j3lp0011.service.SessionService;
import vn.edu.funix.j3lp0011.service.ViewCounterService;

@Controller
@RequiredArgsConstructor
@RequestMapping("/")
public class BlogController {

    private final BlogService blogService;
    private final ViewCounterService viewCounterService;
    private final SessionService sessionService;

    private void addCommonAttributes(Model model) {
        model.addAttribute("socials", blogService.getSocials());
        model.addAttribute("viewCounter", viewCounterService.getFormattedViewsArray());
    }

    @GetMapping({ "/", "/home" })
    public String showHomePage(HttpServletRequest request, Model model) {
        sessionService.handleNewSession(request);
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
}
