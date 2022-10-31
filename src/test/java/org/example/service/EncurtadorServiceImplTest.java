package org.example.service;

import lombok.var;
import org.example.model.Url;
import org.example.model.dto.UrlDTO;
import org.example.repository.EncurtadorRepository;
import org.example.service.impl.EncurtadorServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

class EncurtadorServiceImplTest {

    @InjectMocks
    private EncurtadorServiceImpl service;
    @Mock
    private EncurtadorRepository repository;

    @BeforeEach
    public void setUp(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void gerarUrlTest_Sucesso(){
        when(repository.save(any())).thenReturn(new Url());

        var response = service.gerarUrl(mockUrlDTO());

        assertNotNull(response);
    }

    @Test
    public void gerarUrlTest_UrlNull(){
        when(repository.save(any())).thenReturn(null);

        var response = service.gerarUrl(mockUrlDTO());

        assertNull(response);
    }

    @Test
    public void gerarUrlTest_UrlDTOEmpty(){
        var dto = mockUrlDTO();
        dto.setUrl("");

        var response = service.gerarUrl(dto);

        assertNull(response);
    }

    @Test
    public void getUrlEncurtadaTest(){
        when(repository.findByUrlEncurtada(anyString())).thenReturn(new Url());

        var response = service.getUrlEncurtada("12bu14nb");

        assertNotNull(response);
    }

    @Test
    public void deleteUrlTest(){
        doNothing().when(repository).delete(any());

        assertDoesNotThrow(() -> service.deleteUrl(new Url()));
    }

    @Test
    public void somarAcessoUrlTest(){
        var url = mockUrl();
        when(repository.findByUrlEncurtada(any())).thenReturn(url);

        assertDoesNotThrow(() -> service.somarAcessoUrl(new Url()));
    }

    @Test
    public void getNumeroAcessosTest(){
        when(repository.findByUrlEncurtada(any())).thenReturn(new Url());

        assertDoesNotThrow(() -> service.getNumeroAcessos("123bnuib"));
    }

    private UrlDTO mockUrlDTO(){
        UrlDTO dto = new UrlDTO();
        dto.setUrl("http://teste.com");
        dto.setDataExpiracao("2022-10-31T00:00:00");

        return dto;
    }

    private Url mockUrl(){
        Url url = new Url();
        url.setUrlOriginal("http://teste.com");
        url.setUrlEncurtada("12g3hk41");
        url.setAcessos(1);
        url.setDataCriacao(LocalDateTime.now());
        url.setDataExpiracao(LocalDateTime.MAX);
        url.setId(1L);

        return url;
    }

}