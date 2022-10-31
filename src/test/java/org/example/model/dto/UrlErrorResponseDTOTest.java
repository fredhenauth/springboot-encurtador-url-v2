package org.example.model.dto;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;

class UrlErrorResponseDTOTest {

    @InjectMocks
    private UrlErrorResponseDTO dto;
    @BeforeEach
    public void setUp(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void getUrlErrorDTOTest(){
        dto.getError();
        dto.getStatusUrl();
    }

    @Test
    public void setUrlErrorDTOTest(){
        dto.setError("BAD REQUEST");
        dto.setStatusUrl("404");
    }

    @Test
    public void toStringUrlDTOTest(){
        dto.toString();
    }

}