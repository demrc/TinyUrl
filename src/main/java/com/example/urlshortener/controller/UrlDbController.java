package com.example.urlshortener.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.example.urlshortener.domain.UrlShortenerDto;
import com.example.urlshortener.model.UrlShortener;
import com.example.urlshortener.service.UrlService;

@Controller
@RequestMapping("/urlShortener")
public class UrlDbController {

	@Autowired
	private UrlService urlService;

    public UrlDbController(UrlService urlService) {
		super();
		this.urlService = urlService;
	}
    	
	@GetMapping("/list_urls/{id}")
	public ModelAndView urlShorterForm(@PathVariable String id,
			ModelMap model) {
		List<UrlShortener> url =urlService.findByUserId(Long.valueOf(id));
		List<UrlShortenerDto> urlShortenerDtoList = new ArrayList<UrlShortenerDto>();
		for(UrlShortener u : url) {
		    UrlShortenerDto urlShortenerDto = new UrlShortenerDto();
		    urlShortenerDto.setFullUrl(u.getOriginalUrl());
		    urlShortenerDto.setShortUrl(u.getShortUrl());
		    urlShortenerDto.setUrlId(u.getUrlId());
		    urlShortenerDtoList.add(urlShortenerDto);
		}
		model.addAttribute("listUrls", urlShortenerDtoList);
		return new ModelAndView("urls", model);
	}
	
	@GetMapping(value="/deleteUrl/{id}")
    public String delete(@PathVariable String id){
		Optional<UrlShortener> urlShortener = urlService.findById(Long.valueOf(id));
		urlService.deleteById(Long.valueOf(id));
        return "redirect:/urlShortener/list_urls/"+urlShortener.get().getUser().getId();
    }
}