package com.shortUrl.dto;
import javax.persistence.*;
import java.util.Date;

/**
 *
 * Created by Fergal
 * This is a DTO for the URL object
 *
 */
@Entity
@Table(name="url")
public class UrlDTO {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String shortUrl;

    private String longUrl;

    private Date issuedAt;

    public UrlDTO() {
    }

    @Override
    public String toString() {
        return "UrlDTO{" +
                "id=" + id +
                ", shortUrl='" + shortUrl + '\'' +
                ", longUrl='" + longUrl + '\'' +
                ", issuedAt=" + issuedAt +
                '}';
    }

    public UrlDTO(String shortUrl, String longUrl) {
        this.shortUrl = shortUrl;
        this.longUrl = longUrl;
    }

    public String getShortUrl() {
        return shortUrl;
    }

    public void setShortUrl(String shortUrl) {
        this.shortUrl = shortUrl;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getLongUrl() {
        return longUrl;
    }

    public void setLongUrl(String longUrl) {
        this.longUrl = longUrl;
    }

    public Date getIssuedAt() {
        return issuedAt;
    }

    public void setIssuedAt(Date issuedAt) {
        this.issuedAt = issuedAt;
    }
}
