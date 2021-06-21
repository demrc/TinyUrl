package com.example.urlshortener.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.urlshortener.model.UrlShortener;

@Repository
public interface UrlRepository extends JpaRepository<UrlShortener, Long>{
	
	 public Optional<UrlShortener> findByShortUrl(String shortUrl);
	 public Optional<UrlShortener> findByOriginalUrl(String originalUrl);
	
	@Query(value="Select * from URL_SHORTENER where user_id=:id",nativeQuery=true)
	public List<UrlShortener> findByUserId(long id);
	
}