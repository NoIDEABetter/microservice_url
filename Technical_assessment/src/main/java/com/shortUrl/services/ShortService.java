package com.shortUrl.services;

import com.shortUrl.dto.UrlDTO;

import java.util.List;

/**
 *
 * Created by Fergal
 *
 */
public interface ShortService {

    UrlDTO create(UrlDTO url);

    UrlDTO checkLongUrlExists(String url);

    UrlDTO getOriginalUrlByShort(String url);

    UrlDTO getUrlById(long id);

    List<UrlDTO> getAllUrls();

}
