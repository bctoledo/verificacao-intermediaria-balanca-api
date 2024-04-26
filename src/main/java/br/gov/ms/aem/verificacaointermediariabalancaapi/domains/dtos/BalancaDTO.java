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
public class BalancaDTO {

    private Integer id;
    private String numeroPatrimonio;
    private String numeroSerie;
    private LocalDateTime dtCriacao;

}
