package org.example.controllers;

import lombok.var;
import org.apache.commons.lang3.StringUtils;
import org.example.model.NumeroAcessos;
import org.example.model.dto.UrlDTO;
import org.example.model.dto.UrlErrorResponseDTO;
import org.example.model.dto.UrlResponseDTO;
import org.example.service.EncurtadorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;

/**
 *     Classe de Controller do Projeto.
 */
@RestController
public class EncurtadorController {

    @Autowired
    private EncurtadorService service;

    @PostMapping("/gerarurl")
    public ResponseEntity<?> gerarUrl(@RequestBody UrlDTO dto) {
        var urlRetorno = service.gerarUrl(dto);
        if(urlRetorno != null){
            UrlResponseDTO response = new UrlResponseDTO();
            response.setUrlOriginal(dto.getUrl());
            response.setUrlEncurtada(urlRetorno.getUrlEncurtada());
            response.setDataExpiracao(urlRetorno.getDataExpiracao());

            return new ResponseEntity<UrlResponseDTO>(response, HttpStatus.OK);
        }

        UrlErrorResponseDTO error = new UrlErrorResponseDTO();
        error.setError("Houve um erro no processamento da URL encurtada!");
        error.setStatusUrl("404");

        return new ResponseEntity<UrlErrorResponseDTO>(error, HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/{urlEncurtada}")
    public ResponseEntity<?> redirecionarUrl (@PathVariable String urlEncurtada, HttpServletResponse response) throws IOException {
        if(StringUtils.isEmpty(urlEncurtada)){
            UrlErrorResponseDTO error = new UrlErrorResponseDTO();
            error.setError("URL Inválida!");
            error.setStatusUrl("400");

            return new ResponseEntity<UrlErrorResponseDTO>(error, HttpStatus.BAD_REQUEST);
        }

        var urlRetorno = service.getUrlEncurtada(urlEncurtada);

        if(urlRetorno == null){
            UrlErrorResponseDTO error = new UrlErrorResponseDTO();
            error.setError("URL inexistente ou expirada!");
            error.setStatusUrl("400");

            return new ResponseEntity<UrlErrorResponseDTO>(error, HttpStatus.BAD_REQUEST);
        }

        if(urlRetorno.getDataExpiracao().isBefore(LocalDateTime.now())){
            service.deleteUrl(urlRetorno);
            UrlErrorResponseDTO error = new UrlErrorResponseDTO();
            error.setError("URL expirada, tente gerar um novo código!");
            error.setStatusUrl("400");

            return new ResponseEntity<UrlErrorResponseDTO>(error, HttpStatus.BAD_REQUEST);
        }

        response.sendRedirect(urlRetorno.getUrlOriginal());
        service.somarAcessoUrl(urlRetorno);

        return null;
    }

    @GetMapping("/acessos/{urlEncurtada}")
    public ResponseEntity<?> numeroAcessos(@PathVariable String urlEncurtada){
        if(StringUtils.isEmpty(urlEncurtada)){
            UrlErrorResponseDTO error = new UrlErrorResponseDTO();
            error.setError("URL Inválida!");
            error.setStatusUrl("400");

            return new ResponseEntity<UrlErrorResponseDTO>(error, HttpStatus.BAD_REQUEST);
        }

        var urlRetorno = service.getUrlEncurtada(urlEncurtada);

        if(urlRetorno == null){
            UrlErrorResponseDTO error = new UrlErrorResponseDTO();
            error.setError("URL inexistente ou expirada!");
            error.setStatusUrl("400");

            return new ResponseEntity<UrlErrorResponseDTO>(error, HttpStatus.BAD_REQUEST);
        }

        var numeroAcessos = service.getNumeroAcessos(urlEncurtada);
        NumeroAcessos acessos = new NumeroAcessos();
        acessos.setNumeroAcessos(numeroAcessos);

        return new ResponseEntity<NumeroAcessos>(acessos, HttpStatus.OK);
    }
}
