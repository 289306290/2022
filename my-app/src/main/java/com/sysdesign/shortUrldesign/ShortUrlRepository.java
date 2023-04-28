package com.sysdesign.shortUrldesign;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ShortUrlRepository extends JpaRepository<ShortUrl, Long> {
    ShortUrl findByLongUrl(String longUrl);

    ShortUrl findByShortUrl(String shortUrl);
}
