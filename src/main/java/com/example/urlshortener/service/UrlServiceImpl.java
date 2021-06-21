package com.example.urlshortener.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.urlshortener.model.UrlShortener;
import com.example.urlshortener.model.User;
import com.example.urlshortener.repository.UrlRepository;

@Service
public class UrlServiceImpl implements UrlService {

	
    @Autowired
    private UrlRepository urlRepository;
	
	public UrlServiceImpl(UrlRepository urlRepository) {
		super();
		this.urlRepository=urlRepository;
	}
	
	@Override
    public UrlShortener persistShortUrl(UrlShortener url) {
        UrlShortener urlToRet = urlRepository.save(url);
        return urlToRet;
    }

    @Override
    public void deleteShortUrl(UrlShortener url) {

        urlRepository.delete(url);
    }
    @Override
    public List<UrlShortener> findByUserId(long id) {
    	return urlRepository.findByUserId(id);
    }
    
    @Transactional
    public void saveUrl(UrlShortener entity) {
        urlRepository.save(entity);
    }
    
    public String Shortener(String originalUrl, long userId) {

        final String encryptUrl = getEncryptUrl();
        UrlShortener urlShortener = new UrlShortener();
        urlShortener.setOriginalUrl(originalUrl);
        User user = new User();
        user.setId(userId);
        urlShortener.setUser(user);
        urlShortener.setShortUrl(encryptUrl);
        saveUrl(urlShortener);
 
        return encryptUrl;
    }

    public Optional<UrlShortener> findByShortUrl(String shortUrl) {
        return urlRepository.findByShortUrl(shortUrl);
    }
    
    private String getEncryptUrl(){
    	List<Character> characterList = new ArrayList<>();
    	String allowedString = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    	char[] allowedCharacters = allowedString.toCharArray();
    	for(char character:allowedCharacters) {
    		characterList.add(character);
    	}

        Random random = new Random();
        StringBuilder builder = new StringBuilder();
        for (int i=0; i<4; i++ ){
            builder.append(characterList.get(random.nextInt(characterList.size())));
        }
        return urlRepository.findByShortUrl(builder.toString())
                .map(UrlShortener::getShortUrl)
                .orElse(builder.toString());
    }

	@Override
	public void deleteById(long id) {
		urlRepository.deleteById(id);
	}

	@Override
	public Optional<UrlShortener> findById(long id) {
		return urlRepository.findById(id);
	}
}