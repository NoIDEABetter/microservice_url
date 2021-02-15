package com.zuul.gateway;

import com.netflix.zuul.context.RequestContext;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment= SpringBootTest.WebEnvironment.RANDOM_PORT, classes = Application.class)
public class GatewayTests {
    @Autowired
    private TestRestTemplate rest;

    static ConfigurableApplicationContext shortService;

    @BeforeAll
    public static void startBookService() {
        shortService = SpringApplication.run(shortService.class,
                "--server.port=8090");
    }

    @AfterAll
    public static void closeBookService() {
        shortService.close();
    }

    @BeforeEach
    public void setup() {
        RequestContext.testSetCurrentContext(new RequestContext());
    }

    @Test
    public void test() {
        String resp = rest.getForObject("/XYZ1uOn", String.class);
    }

    @Configuration
    @EnableAutoConfiguration
    @RestController
    static class shortService {
        @RequestMapping("/XYZ1uOn")
        public ResultMatcher getAvailable() {
            return status().isNotFound();
        }
    }
}
