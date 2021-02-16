package com.shortUrl.services;

import com.shortUrl.services.impl.ConversionServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ConversionServiceTest {

    @Autowired
    private ConversionService conversionService;

    @Test
    public void test_encode() {
        long id = new Date().getTime();
        String small = conversionService.encode(id);
        assertThat(small.length()).isEqualTo(7);
    }

}
