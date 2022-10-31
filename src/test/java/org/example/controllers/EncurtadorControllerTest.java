package org.example.controllers;

import lombok.var;
import org.example.model.NumeroAcessos;
import org.example.model.Url;
import org.example.model.dto.UrlDTO;
import org.example.service.EncurtadorService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

class EncurtadorControllerTest {

    @InjectMocks
    private EncurtadorController controller;
    @Mock
    private EncurtadorService service;
    @Mock
    private HttpServletResponse httpServletResponse;

    @BeforeEach
    public void setUp(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void gerarUrlTest_Sucesso(){
        when(service.gerarUrl(any())).thenReturn(new Url());

        var response = controller.gerarUrl(new UrlDTO());

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    public void gerarUrlTest_UrlNull(){
        when(service.gerarUrl(any())).thenReturn(null);

        var response = controller.gerarUrl(new UrlDTO());

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }

    @Test
    public void redirecionarUrlTest_Sucesso() throws IOException {
        var url = mockUrl();
        when(service.getUrlEncurtada(anyString())).thenReturn(url);
        doNothing().when(httpServletResponse).sendRedirect(url.getUrlOriginal());

        var response = controller.redirecionarUrl("11hg3b", httpServletResponse);

        assertNull(response);
    }

    @Test
    public void redirecionarUrlTest_UrlNull() throws IOException {
        String url = "";

        var response = controller.redirecionarUrl(url, httpServletResponse);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }

    @Test
    public void redirecionarUrlTest_UrlRetornoNull() throws IOException {
        when(service.getUrlEncurtada(anyString())).thenReturn(null);

        var response = controller.redirecionarUrl("null", httpServletResponse);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }

    @Test
    public void redirecionarUrlTest_DataExpiracaoExcedida() throws IOException {
        var url = mockUrl();
        url.setDataExpiracao(LocalDateTime.MIN);
        when(service.getUrlEncurtada(anyString())).thenReturn(url);

        var response = controller.redirecionarUrl("null", httpServletResponse);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }

    @Test
    public void numeroAcessosTest_Sucesso(){
        var url = mockUrl();
        NumeroAcessos acessos = new NumeroAcessos();
        acessos.setNumeroAcessos(4);
        when(service.getUrlEncurtada(anyString())).thenReturn(url);
        when(service.getNumeroAcessos(url.getUrlEncurtada())).thenReturn(1);

        var response = controller.numeroAcessos("1iuhbb2i1");

        assertNotNull(controller);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    public void numeroAcessosTest_UrlNull(){
        String url = "";

        var response = controller.numeroAcessos(url);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }

    @Test
    public void numeroAcessosTest_UrlRetornoNull() throws IOException {
        when(service.getUrlEncurtada(anyString())).thenReturn(null);

        var response = controller.numeroAcessos("null");

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }

    private Url mockUrl(){
        Url url = new Url();
        url.setUrlOriginal("http://teste.com");
        url.setUrlEncurtada("1bdg3632");
        url.setDataCriacao(LocalDateTime.now());
        url.setDataExpiracao(LocalDateTime.MAX);
        url.setAcessos(0);
        url.setId(1L);

        return url;
    }
}