package com.sysdesign.shortUrldesign;

import org.redisson.api.RLock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.Date;
import java.util.List;

@Service
public class ShortUrlService {
    @Autowired
    private ShortUrlRepository shortUrlRepository;
    @Autowired
    private ShortUrlAccessLogRepository shortUrlAccessLogRepository;

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Autowired
    private RedissonLockManager redissonLockManager;


    @Async
    public String generateShortUrl(String longUrl) {
        RLock lock = redissonLockManager.getLock(longUrl);
        String shortUrlStr;
        try{
            lock.lock();
            // 短链接生成逻辑
            ShortUrl shortUrl = shortUrlRepository.findByLongUrl(longUrl);
            if (shortUrl != null) {
                return shortUrl.getShortUrl();
            }
            shortUrlStr = ShortUrlGenerator.generate(longUrl);
            ShortUrl newShortUrl = new ShortUrl();
            newShortUrl.setShortUrl(shortUrlStr);
            newShortUrl.setLongUrl(longUrl);
            newShortUrl.setCreateTime(new Date());
            newShortUrl.setUpdateTime(new Date());
            shortUrlRepository.save(newShortUrl);
        } finally {
            lock.unlock();
        }

        return shortUrlStr;
    }

    @Async
    public void logAccess(ShortUrlAccessLog accessLog) {
        shortUrlAccessLogRepository.save(accessLog);
    }


    @Cacheable(value = "shortUrl", key = "#shortUrl")
    public ShortUrl getByShortUrl(String shortUrl) {
        RLock lock = redissonLockManager.getLock(shortUrl);
        ShortUrl shortUrlObj ;
        try {
            lock.lock();
            shortUrlObj = shortUrlRepository.findByShortUrl(shortUrl);
            if (null != shortUrlObj) {
                ShortUrlAccessLog accessLog = new ShortUrlAccessLog();
                accessLog.setShortUrl(shortUrl);
                accessLog.setAccessTime(new Date());
                shortUrlAccessLogRepository.save(accessLog);
            }
        }finally {
            lock.unlock();
        }
        return shortUrlObj;
    }

    public List<ShortUrlAccessLog> getAccessLogsByShortUrl(String shortUrl) {
        List<ShortUrlAccessLog> accessLogs = (List<ShortUrlAccessLog>) redisTemplate.opsForValue().get("accessLogs:" + shortUrl);
        if (accessLogs == null) {
            accessLogs = shortUrlAccessLogRepository.findByShortUrl(shortUrl);
            redisTemplate.opsForValue().set("accessLogs:" + shortUrl, accessLogs, Duration.ofMinutes(10)); // 缓存10分钟
        }
        return accessLogs;
    }
}