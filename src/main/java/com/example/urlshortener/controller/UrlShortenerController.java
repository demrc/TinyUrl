package com.example.urlshortener.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.validator.routines.UrlValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.urlshortener.domain.UrlShortenerDto;
import com.example.urlshortener.service.UrlServiceImpl;

@EnableWebMvc
@Controller
@RequestMapping("/index")
public class UrlShortenerController{
   
	@Autowired
    private final UrlServiceImpl urlServiceImpl;

    public UrlShortenerController(UrlServiceImpl urlServiceImpl) {
		super();
		this.urlServiceImpl = urlServiceImpl;
	}

    @ModelAttribute("urlShortener")
	public UrlShortenerDto urlDto() {
		return new UrlShortenerDto();
	}
	
	@GetMapping
	public ModelAndView urlShorterForm(HttpServletRequest request, @ModelAttribute("id") String id, 
			ModelMap model) {
		UrlShortenerDto dto = new UrlShortenerDto();
		dto.setUserId(Long.valueOf(id));
		model.addAttribute("urlShortener",dto);
		return new ModelAndView("index", model);
	}
   
    @GetMapping("/list_urls")
    public String viewUrlsList() {
    	return "URL_SHORTENER";
    }
    
    @PostMapping(value = "/urlShortener")
    public String Shortener(RedirectAttributes redirectAttributes, @ModelAttribute("urlShortenerDto") UrlShortenerDto urlShortenerDto){

        if (!UrlValidator.getInstance().isValid(urlShortenerDto.getFullUrl())){
            redirectAttributes.addFlashAttribute("originalUrl", urlShortenerDto.getFullUrl());
            return "redirect:/";
        }

        String shortUrl = urlServiceImpl.Shortener(urlShortenerDto.getFullUrl(),urlShortenerDto.getUserId());
        String redirectUrl ="index/redirect/" + shortUrl;
        
        return "redirect:/"+redirectUrl;
    }

    @GetMapping("/redirect/{shortUrl}")
    public String goToUrl(@PathVariable String shortUrl){
        return urlServiceImpl.findByShortUrl(shortUrl)
                .map(urlShortener -> "redirect:" + urlShortener.getOriginalUrl())
                .orElse("redirect:/404");
    }
}