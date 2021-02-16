package com.shortUrl.web;

import com.shortUrl.JsonUtil;
import com.shortUrl.dto.UrlDTO;
import com.shortUrl.services.ShortService;
import com.shortUrl.web.controllers.ShortUrlController;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.mockito.internal.verification.VerificationModeFactory;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;


import java.util.Arrays;
import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(value = ShortUrlController.class)
public class ShortControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private ShortService service;

    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void whenPostShorten_thenCreateShort() throws Exception {
        UrlDTO url = new UrlDTO();
        url.setLongUrl("https://github.com/spring-cloud/spring-cloud-netflix");
        given(service.create(Mockito.any())).willReturn(url);

        mvc.perform(post("/shorten").contentType(MediaType.APPLICATION_JSON).content(JsonUtil.toJson(url))).andExpect(status().isOk());
        verify(service, VerificationModeFactory.times(1)).create(Mockito.any());
        reset(service);
    }

    @Test
    public void givenShortUrl_thenReturnLongUrl() throws Exception {
        UrlDTO url = new UrlDTO();
        url.setLongUrl("https://github.com/spring-cloud/spring-cloud-netflix");
        url.setShortUrl("2yOMoQd");
        given(service.create(url)).willReturn(url);

        mvc.perform(get("/2yOMoQd").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isNotFound());
        verify(service, VerificationModeFactory.times(1)).getOriginalUrlByShort("2yOMoQd");
        reset(service);
    }
}
