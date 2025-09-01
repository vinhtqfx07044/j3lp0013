package vn.edu.funix.j3lp0011.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import vn.edu.funix.j3lp0011.service.BlogService;

@Controller
@RequiredArgsConstructor
public class AboutMeController {

    private final BlogService blogService;

    @GetMapping("/about-me")
    public String showAboutMePage(Model model) {
        model.addAttribute("aboutMe", blogService.getAboutMe());
        return "about-me";
    }
}
