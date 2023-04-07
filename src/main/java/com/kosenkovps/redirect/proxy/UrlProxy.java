package com.kosenkovps.redirect.proxy;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "urls",
        url = "${name.service.url}")
public interface UrlProxy {
    @PostMapping("short")
    String convertToShortUrl(@RequestBody String request);

    @GetMapping("{shortUrl}")
    ResponseEntity<String> getAndRedirect(@PathVariable String shortUrl, @RequestParam Boolean remote);
}