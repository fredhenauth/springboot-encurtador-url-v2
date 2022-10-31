package org.example.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;

class UrlTest {
    @InjectMocks
    private Url url;

    @BeforeEach
    public void setUp(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void urlSetTest(){
        url.setId(1L);
        url.setUrlOriginal("http://teste.com");
        url.setUrlEncurtada("1gy2g3");
        url.setDataCriacao(LocalDateTime.now());
        url.setDataExpiracao(LocalDateTime.MAX);
        url.setAcessos(1);
    }

    @Test
    public void urlGetTest(){
        url.getId();
        url.getUrlOriginal();
        url.getUrlEncurtada();
        url.getDataCriacao();
        url.getDataExpiracao();
        url.getAcessos();
    }

    @Test
    public void urlToStringTest(){
        url.toString();
    }

}