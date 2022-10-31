package org.example.model.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class UrlResponseDTO {
    private String urlOriginal;
    private String urlEncurtada;
    private LocalDateTime dataExpiracao;
}
