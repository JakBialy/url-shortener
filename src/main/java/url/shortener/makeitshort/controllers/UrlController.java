package url.shortener.makeitshort.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import url.shortener.makeitshort.dtos.UrlDto;
import url.shortener.makeitshort.engines.CodingEngine;

@Controller
public class UrlController {

    private CodingEngine codingEngine;

    public UrlController(CodingEngine codingEngine) {
        this.codingEngine = codingEngine;
    }

    @PostMapping("/createUrl")
    public String putUrl(@ModelAttribute UrlDto urlDto, Model model) {
        String url = ServletUriComponentsBuilder.fromCurrentRequestUri().toUriString();
        int lastIndex = url.lastIndexOf("/");
        String justEmptyUrl = url.substring(0, lastIndex + 1);

        model.addAttribute("generatedUrl", justEmptyUrl + codingEngine.generateShorterUrl(urlDto.getUrl()));
        return "GeneratedUrlPage";
    }

    @GetMapping("")
    public String homePage(Model model) {
        model.addAttribute("urlDto", new UrlDto());
        return "Page";
    }

    @GetMapping("/{code}")
    public ModelAndView getRealUrl(@PathVariable String code, ModelMap modelMap) {
        String realUrl = codingEngine.getBackRealUrl(code);
        return new ModelAndView("redirect:" + realUrl);
    }

}
