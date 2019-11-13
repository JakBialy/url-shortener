package url.shortener.makeitshort.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import url.shortener.makeitshort.dtos.UrlDto;
import url.shortener.makeitshort.engines.implementations.CodingService;
import url.shortener.makeitshort.engines.implementations.SearchEngine;

@Controller
public class UrlController {
    private CodingService codingService;
    private SearchEngine searchEngine;

    public UrlController(CodingService codingService, SearchEngine searchEngine) {
        this.codingService = codingService;
        this.searchEngine = searchEngine;
    }

    @PostMapping("/createUrl")
    public String putUrl(@ModelAttribute UrlDto urlDto, Model model) {
        model.addAttribute("generatedUrl", codingService.processUrlToGetCode(urlDto.getUrl(), urlDto.getTypeOfEngine()));
        return "GeneratedUrlPage";
    }

    @GetMapping("")
    public String homePage(Model model) {
        model.addAttribute("urlDto", new UrlDto());
        return "Page";
    }

    @GetMapping("/{code}")
    public ModelAndView getRealUrl(@PathVariable String code) {
        return new ModelAndView("redirect:" + searchEngine.getBackRealUrl(code));
    }

}
