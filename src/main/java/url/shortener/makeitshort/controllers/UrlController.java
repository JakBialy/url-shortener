package url.shortener.makeitshort.controllers;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import url.shortener.makeitshort.dtos.UrlDto;
import url.shortener.makeitshort.engines.CodingEngine;

@Controller
public class UrlController {

    private CodingEngine codingEngine;

    public UrlController(@Qualifier("Base64Engine") CodingEngine codingEngine) {
        this.codingEngine = codingEngine;
    }

    @PostMapping("/createUrl")
    public String putUrl(@ModelAttribute UrlDto urlDto, Model model) {
        model.addAttribute("generatedUrl", codingEngine.processUrlToGetCode(urlDto.getUrl()));
        return "GeneratedUrlPage";
    }

    @GetMapping("")
    public String homePage(Model model) {
        model.addAttribute("urlDto", new UrlDto());
        return "Page";
    }

    @GetMapping("/{code}")
    public ModelAndView getRealUrl(@PathVariable String code) {
        return new ModelAndView("redirect:" + codingEngine.getBackRealUrl(code));
    }

}
