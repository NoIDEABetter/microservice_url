package com.shortUrl.services.impl;

import com.shortUrl.services.ConversionService;
import org.springframework.stereotype.Service;

/**
 *
 * @author Fergal
 *
 */
@Service
public class ConversionServiceImpl implements ConversionService {

    private static final String ALPHABET = "abcdefghijklmnopqrstuvwxyz0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final int BASE = ALPHABET.length();

    /**
     * This method provides the short url based on the time that the request was made.
     *
     * @param num
     * @return String
     */
    @Override
    public String encode(long num) {
        StringBuilder sb = new StringBuilder();
        while (num > 0) {
            sb.append(ALPHABET.charAt((int) (num % BASE)));
            num /= BASE;
        }
        return sb.reverse().toString();
    }
}
