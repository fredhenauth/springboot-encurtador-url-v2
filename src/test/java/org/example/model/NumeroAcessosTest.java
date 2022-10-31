package org.example.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;

class NumeroAcessosTest {

    @InjectMocks
    private NumeroAcessos numeroAcessos;

    @BeforeEach
    public void setUp(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void getnumeroAcessosTest(){
        numeroAcessos.getNumeroAcessos();
    }

    @Test
    public void setnumeroAcessosTest(){
        numeroAcessos.setNumeroAcessos(2);
    }

}