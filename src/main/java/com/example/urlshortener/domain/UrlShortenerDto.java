package com.example.urlshortener.domain;

public class UrlShortenerDto {
	private long userId;
	private long urlId;
	private String fullUrl;
	private String shortUrl;
	
	public UrlShortenerDto() {
		
	}
	
	public UrlShortenerDto(long userId, String fullUrl,String shortUrl,long urlId) {
		super();
		this.userId = userId;
		this.shortUrl=shortUrl;
		this.fullUrl = fullUrl;
		this.urlId=urlId;
	}


	public String getShortUrl() {
		return shortUrl;
	}


	public void setShortUrl(String shortUrl) {
		this.shortUrl = shortUrl;
	}


	public long getUserId() {
		return userId;
	}
	public void setUserId(long userId) {
		this.userId = userId;
	}
	public String getFullUrl() {
		return fullUrl;
	}
	public void setFullUrl(String fullUrl) {
		this.fullUrl = fullUrl;
	}


	public long getUrlId() {
		return urlId;
	}


	public void setUrlId(long urlId) {
		this.urlId = urlId;
	}
	
}
