package com.shortUrl.services;

import com.shortUrl.dto.UrlDTO;
import com.shortUrl.persistence.UrlRepository;
import com.shortUrl.services.impl.ShortServiceImpl;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.internal.verification.VerificationModeFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;


import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ShortServiceTest {

    @TestConfiguration
    static class ShortServiceImplTestContextConfiguration {
        @Bean
        public ShortService shortService() {
            return new ShortServiceImpl();
        }
    }

    @Autowired
    private ShortService shortService;

    @MockBean
    private UrlRepository urlRepository;

    @Before
    public void setUp() {
        UrlDTO url = new UrlDTO();
        url.setId(11L);
        url.setLongUrl("https://github.com/spring-cloud/spring-cloud-netflix");
        url.setShortUrl("2yOMoQd");

        UrlDTO diffUrl = new UrlDTO();
        diffUrl.setId(12L);
        diffUrl.setLongUrl("https://kubernetes.io/docs/tasks/manage-kubernetes-objects/update-api-object-kubectl-patch/");
        diffUrl.setShortUrl("2yQdNsm");

        UrlDTO StacckOUrl = new UrlDTO();
        diffUrl.setId(13L);
        StacckOUrl.setLongUrl("https://kubernetes.io/docs/tasks/configmap-secret/managing-secret-using-kustomize/");
        StacckOUrl.setShortUrl("2yQejFV");

        List<UrlDTO> allUrls = Arrays.asList(url, diffUrl, StacckOUrl);

        Mockito.when(urlRepository.findById(url.getId())).thenReturn(url);
        Mockito.when(urlRepository.findById(diffUrl.getId())).thenReturn(diffUrl);
        Mockito.when(urlRepository.findById(20L)).thenReturn(null);
        Mockito.when(urlRepository.findById(url.getId())).thenReturn(url);
        Mockito.when(urlRepository.findAll()).thenReturn(allUrls);
        Mockito.when(urlRepository.findById(-99L)).thenReturn(null);
    }

    @Test
    public void createUrl() {
        UrlDTO url = new UrlDTO();
        url.setLongUrl("https://kubernetes.io/docs/tasks/configmap-secret//");
        shortService.create(url);
        assertThat(url.getShortUrl()).isNotEmpty();
    }

    @Test
    public void createUrlEmpty() {
        UrlDTO url = new UrlDTO();
        url.setLongUrl("");
        shortService.create(url);
        assertThat(url.getShortUrl()).isNotEmpty();
    }

    @Test
    public void whenNonExistingUrl_thenUrlShouldNotExist() {
        UrlDTO url = shortService.checkLongUrlExists("https://kubernetes.io/docs/tasks/configmap-secret//");
        assertThat(url).isNull();
    }

    @Test
    public void whenExistingUrl_thenUrlShouldExist() {
        UrlDTO url = shortService.checkLongUrlExists("https://kubernetes.io/docs/tasks/configmap-secret/managing-secret-using-kustomize/");
        assertThat(url.getShortUrl()).isEqualTo("2yQejFV");
    }

    @Test
    public void whenValidId_thenUrlShouldBeFound() {
        UrlDTO fromDb = shortService.getUrlById(11L);
        assertThat(fromDb.getLongUrl()).isEqualTo("https://github.com/spring-cloud/spring-cloud-netflix");

        verifyFindByIdIsCalledOnce();
    }

    @Test
    public void whenInValidId_thenEmployeeShouldNotBeFound() {
        UrlDTO fromDb = shortService.getUrlById(-99L);
        verifyFindByIdIsCalledOnce();
        assertThat(fromDb).isNull();
    }

    @Test
    public void getOriginalByShortTest() {
        UrlDTO fromDb = shortService.getOriginalUrlByShort("2yQejFV");
        assertThat(fromDb.getLongUrl()).isEqualTo("https://kubernetes.io/docs/tasks/configmap-secret/managing-secret-using-kustomize/");
    }

    @Test
    public void fakeGetByShortTest() {
        UrlDTO fromDb = shortService.getOriginalUrlByShort("2yXeiFV");
        assertThat(fromDb).isNull();
    }

    @Test
    public void given3Urls_thenReturn3Records() {
        UrlDTO url = new UrlDTO();
        url.setId(11L);
        url.setLongUrl("https://github.com/spring-cloud/spring-cloud-netflix");
        url.setShortUrl("2yOMoQd");

        UrlDTO diffUrl = new UrlDTO();
        diffUrl.setId(12L);
        diffUrl.setLongUrl("https://kubernetes.io/docs/tasks/manage-kubernetes-objects/update-api-object-kubectl-patch/");
        diffUrl.setShortUrl("2yQdNsm");

        UrlDTO StacckOUrl = new UrlDTO();
        diffUrl.setId(13L);
        StacckOUrl.setLongUrl("https://kubernetes.io/docs/tasks/configmap-secret/managing-secret-using-kustomize/");
        StacckOUrl.setShortUrl("2yQejFV");

        List<UrlDTO> allUrls = shortService.getAllUrls();
        verifyFindAllUrlsIsCalledOnce();
        assertThat(allUrls).hasSize(3).extracting(UrlDTO::getShortUrl).contains(url.getShortUrl(), diffUrl.getShortUrl(), StacckOUrl.getShortUrl());
    }

    private void verifyFindByIdIsCalledOnce() {
        Mockito.verify(urlRepository, VerificationModeFactory.times(1)).findById(Mockito.anyLong());
        Mockito.reset(urlRepository);
    }

    private void verifyFindAllUrlsIsCalledOnce() {
        Mockito.verify(urlRepository, VerificationModeFactory.times(1)).findAll();
        Mockito.reset(urlRepository);
    }
}
