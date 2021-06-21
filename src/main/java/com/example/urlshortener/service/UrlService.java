package com.example.urlshortener.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.urlshortener.model.UrlShortener;

@Service
public interface UrlService {
	
	public UrlShortener persistShortUrl(UrlShortener url);
	
	public void  deleteShortUrl(UrlShortener url);

	List<UrlShortener> findByUserId(long id);

	public Object findByShortUrl(String shortUrl);
		
	public void deleteById(long userId);
	
	public Optional<UrlShortener> findById(long id);
}
