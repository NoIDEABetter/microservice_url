package com.shortUrl.services.impl;

import com.shortUrl.dto.UrlDTO;
import com.shortUrl.persistence.UrlRepository;
import com.shortUrl.services.ConversionService;
import com.shortUrl.services.ShortService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 *
 * Created by Fergal
 * @Version 1.0
 *
 */
@Service
public class ShortServiceImpl implements ShortService {

    @Autowired
    private ConversionService conversionService;

    @Autowired
    private UrlRepository urlRepository;

    @Override
    public UrlDTO create(UrlDTO url){
        long id = new Date().getTime();
        url.setShortUrl(conversionService.encode(id));
        url.setIssuedAt(new Date());
        return urlRepository.save(url);
    }

    @Override
    public UrlDTO checkLongUrlExists(String url){
        List<UrlDTO> urls = urlRepository.findAll();
        for(int i=0;i<urls.size();i++){
            UrlDTO urlObj = urls.get(i);
            if(urlObj.getLongUrl().equals(url)){
                return urlObj;
            }
        }
        return null;
    }

    @Override
    public UrlDTO getOriginalUrlByShort(String url) {
        List<UrlDTO> urls = urlRepository.findAll();
        for(int i=0;i<urls.size();i++){
            UrlDTO urlObj = urls.get(i);
            if(urlObj.getShortUrl().equals(url)){
                return urlObj;
            }
        }
        return null;
    }

    @Override
    public UrlDTO getUrlById(long id) {
        return urlRepository.findById(id);
    }

    @Override
    public List<UrlDTO> getAllUrls() {
        return urlRepository.findAll();
    }

}
