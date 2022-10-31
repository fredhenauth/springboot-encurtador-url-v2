package org.example.service;

import org.example.model.Url;
import org.example.model.dto.UrlDTO;
import org.springframework.stereotype.Service;

/**
 *     Classe de Serviço do Projeto.
 */
@Service
public interface EncurtadorService {
    public Url gerarUrl(UrlDTO dto);
    public Url persistirUrl(Url url);
    public Url getUrlEncurtada(String url);
    public void deleteUrl(Url url);
    public void somarAcessoUrl(Url url);
    public Integer getNumeroAcessos(String urlEncurtada);
}
