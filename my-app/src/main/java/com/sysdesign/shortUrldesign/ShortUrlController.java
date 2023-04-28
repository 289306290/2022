package com.sysdesign.shortUrldesign;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

@Controller
public class ShortUrlController {
    @Autowired
    private ShortUrlService shortUrlService;

    @GetMapping("/{shortUrl}")
    public String redirectToLongUrl(@PathVariable String shortUrl, HttpServletRequest request) {
        ShortUrl shortUrlObj = shortUrlService.getByShortUrl(shortUrl);
        if (shortUrlObj == null) {
            throw new RuntimeException("");
        }
        String longUrl = shortUrlObj.getLongUrl();// 记录访问统计信息//
        ShortUrlAccessLog accessLog = new ShortUrlAccessLog();
        accessLog.setShortUrl(shortUrl);
        accessLog.setAccessTime(new Date());
        accessLog.setIp(request.getRemoteAddr());
        accessLog.setReferer(request.getHeader("referer"));
        accessLog.setUserAgent(request.getHeader("user-agent"));
        shortUrlService.logAccess(accessLog);
        return "redirect:" + longUrl;
    }
}