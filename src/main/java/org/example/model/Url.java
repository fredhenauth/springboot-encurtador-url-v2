package org.example.model;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Url {
    @Id
    @GeneratedValue
    private Long id;
    @Lob
    private String urlOriginal;
    private String urlEncurtada;
    private LocalDateTime dataCriacao;
    private LocalDateTime dataExpiracao;
    private Integer acessos;
}
