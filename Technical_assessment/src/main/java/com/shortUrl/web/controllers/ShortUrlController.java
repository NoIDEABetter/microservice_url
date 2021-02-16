package com.shortUrl.web.controllers;

import com.shortUrl.dto.UrlDTO;
import com.shortUrl.web.exception.urlNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import com.shortUrl.services.ShortService;

import java.net.URI;

/**
 *
 * Created by Fergal
 *
 */
@Controller
@RestController
public class ShortUrlController {

    @Autowired
    private ShortService shortService;

    @PostMapping("/shorten")
    public ResponseEntity<UrlDTO> convertToShortUrl(@RequestBody UrlDTO request) {
        UrlDTO newUrl = shortService.checkLongUrlExists(request.getLongUrl());
        if(newUrl!=null){
            return ResponseEntity.ok(newUrl);
        }else {
            return ResponseEntity.ok(shortService.create(request));
        }
    }

    @GetMapping(value = "{shortUrl}")
    public ResponseEntity<Void> getAndRedirect(@PathVariable String shortUrl) throws urlNotFoundException {
        UrlDTO url = shortService.getOriginalUrlByShort(shortUrl);
        try{
            return ResponseEntity.status(HttpStatus.FOUND)
                    .location(URI.create(url.getLongUrl()))
                    .build();
        } catch (NullPointerException e){
            throw new urlNotFoundException();
        }

    }
}
