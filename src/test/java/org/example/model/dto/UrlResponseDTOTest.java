package org.example.model.dto;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class UrlResponseDTOTest {

    @InjectMocks
    private UrlResponseDTO dto;
    @BeforeEach
    public void setUp(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void getUrlResponseDTOTest(){
        dto.getUrlEncurtada();
        dto.getUrlOriginal();
        dto.getDataExpiracao();
    }

    @Test
    public void setUrlResponseDTOTest(){
        dto.setUrlEncurtada("dhudhuiqwh");
        dto.setUrlOriginal("uqwihdioq");
        dto.setDataExpiracao(LocalDateTime.now());
    }

    @Test
    public void toStringUrlResponseDTOTest(){
        dto.toString();
    }

}