package com.sysdesign.shortUrldesign;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ShortUrlAccessLogRepository extends JpaRepository<ShortUrlAccessLog, Long> {
    List<ShortUrlAccessLog> findByShortUrl(String shortUrl);
}
