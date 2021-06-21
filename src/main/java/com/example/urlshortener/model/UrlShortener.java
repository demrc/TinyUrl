package com.example.urlshortener.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="URL_SHORTENER")
public class UrlShortener{

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
    private long urlId;
   
	@Column(name="originalUrl")
    private String originalUrl;
	
	@Column(name = "shortUrl",nullable = false,unique = true)
    private String shortUrl;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "userId", referencedColumnName = "id", nullable = false)
	private User user;
	
    public UrlShortener(long urlId, String originalUrl, String shortUrl) {
        this.urlId=urlId;
        this.originalUrl = originalUrl;
        this.shortUrl = shortUrl;
    }
    
    public UrlShortener() {
    }

    
    public String getOriginalUrl() {
        return originalUrl;
    }

    public void setOriginalUrl(String originalUrl) {
        this.originalUrl = originalUrl;
    }
       
    @Override
    public String toString() {
        return "Url{" +
                "id=" + urlId +
                ", originalUrl='" + originalUrl + '\'' +
                ", shortUrl='" + shortUrl + '\'' +
                '}';
    }

	public long getUrlId() {
		return urlId;
	}

	public void setUrlId(long urlId) {
		this.urlId = urlId;
	}

	public String getShortUrl() {
		return shortUrl;
	}

	public void setShortUrl(String shortUrl) {
		this.shortUrl = shortUrl;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
}