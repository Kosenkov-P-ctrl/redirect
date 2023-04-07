package com.kosenkovps.redirect.controller;

import com.kosenkovps.redirect.proxy.UrlProxy;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.Objects;

@RestController
@RequestMapping("/api/v2")
@RequiredArgsConstructor
@Slf4j
public class RedirectController {
    private final UrlProxy urlProxy;
    @PostMapping("shrt")
    public ResponseEntity<String> convertToShortUrl(@RequestBody String request){
        return ResponseEntity.ok(urlProxy.convertToShortUrl(request));
    }

    @GetMapping("{shortUrl}")
    @Cacheable(value = "urls", key = "#shortUrl", sync = true)
    public ResponseEntity<?> getAndRedirect(@PathVariable String shortUrl){
        log.info(urlProxy.getAndRedirect(shortUrl, true).getBody());
        return ResponseEntity.status(HttpStatus.FOUND)
                .location(URI.create(Objects.requireNonNull(urlProxy.getAndRedirect(shortUrl, true).getBody())))
                .build();
    }
}
