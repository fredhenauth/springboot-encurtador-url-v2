package org.example.service.impl;

import com.google.common.hash.Hashing;
import lombok.var;
import org.apache.commons.lang3.StringUtils;
import org.example.model.Url;
import org.example.model.dto.UrlDTO;
import org.example.repository.EncurtadorRepository;
import org.example.service.EncurtadorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;

@Component
public class EncurtadorServiceImpl implements EncurtadorService {

    @Autowired
    private EncurtadorRepository repository;

    @Override
    public Url gerarUrl(UrlDTO dto){
        if(StringUtils.isNotEmpty(dto.getUrl())){
            var urlEncurtada = encurtarUrl(dto.getUrl());
            Url urlPersistida = new Url();
            urlPersistida.setUrlEncurtada(urlEncurtada);
            urlPersistida.setUrlOriginal(dto.getUrl());
            urlPersistida.setDataCriacao(LocalDateTime.now());
            urlPersistida.setDataExpiracao(getDataExpiracao(dto.getDataExpiracao(), urlPersistida.getDataCriacao()));
            urlPersistida.setAcessos(0);
            var urlRetorno = persistirUrl(urlPersistida);

            if(urlRetorno != null) {
                return urlRetorno;
            }

            return null;
        }
        return null;
    }

    @Override
    public Url persistirUrl(Url url){
        var urlRetorno = repository.save(url);
        return urlRetorno;
    }

    @Override
    public Url getUrlEncurtada(String url){
        var urlRetorno = repository.findByUrlEncurtada(url);
        return urlRetorno;
    }

    @Override
    public void deleteUrl(Url url){
        repository.delete(url);
    }

    @Override
    public void somarAcessoUrl(Url url){
        var urlRepository = repository.findByUrlEncurtada(url.getUrlEncurtada());
        var numeroAcessos = urlRepository.getAcessos() + 1;
        repository.findById(urlRepository.getId()).map(
                record -> {
                    record.setAcessos(numeroAcessos);
                    Url atualizado = repository.save(record);
                    return null;
                });
    }

    @Override
    public Integer getNumeroAcessos(String urlEncurtada){
        var urlRepository = repository.findByUrlEncurtada(urlEncurtada);
        return urlRepository.getAcessos();
    }

    private String encurtarUrl(String url){
        LocalDateTime time = LocalDateTime.now();
        var encodedUrl = Hashing.murmur3_32()
                .hashString(url.concat(time.toString()), StandardCharsets.UTF_8)
                .toString();
        return encodedUrl;
    }

    private LocalDateTime getDataExpiracao(String dataExpiracao, LocalDateTime dataCriacao){
        if(StringUtils.isBlank(dataExpiracao)){
            return dataCriacao.plusSeconds(180);
        }
        return LocalDateTime.parse(dataExpiracao);
    }
}
