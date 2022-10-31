package org.example.model.dto;

import org.example.model.NumeroAcessos;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;

class UrlDTOTest {

    @InjectMocks
    private UrlDTO urlDTO;

    @BeforeEach
    public void setUp(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void getUrlDTOTest(){
        urlDTO.getUrl();
        urlDTO.getDataExpiracao();
    }

    @Test
    public void setUrlDTOTest(){
        urlDTO.setUrl("123gui2");
        urlDTO.setDataExpiracao("28318923128");
    }

    @Test
    public void toStringUrlDTOTest(){
        urlDTO.toString();
    }

}