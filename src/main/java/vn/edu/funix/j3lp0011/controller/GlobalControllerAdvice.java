package vn.edu.funix.j3lp0011.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;
import vn.edu.funix.j3lp0011.dto.SocialDTO;
import vn.edu.funix.j3lp0011.service.BlogService;
import vn.edu.funix.j3lp0011.service.ViewCounterService;

import java.util.List;

@ControllerAdvice
@RequiredArgsConstructor
public class GlobalControllerAdvice {

    private final BlogService blogService;
    private final ViewCounterService viewCounterService;

    @ModelAttribute("socials")
    public List<SocialDTO> addSocialsToModel() {
        return blogService.getSocials();
    }

    @ModelAttribute("viewCounter")
    public String[] addViewCounterToModel() {
        return viewCounterService.getFormattedViewsArray();
    }
}