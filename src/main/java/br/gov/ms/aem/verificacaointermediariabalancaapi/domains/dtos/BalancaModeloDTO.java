package br.gov.ms.aem.verificacaointermediariabalancaapi.domains.dtos;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BalancaModeloDTO {
    private Integer id;
    private String marca;
    private String modelo;
    private LocalDateTime dtCriacao;
}
