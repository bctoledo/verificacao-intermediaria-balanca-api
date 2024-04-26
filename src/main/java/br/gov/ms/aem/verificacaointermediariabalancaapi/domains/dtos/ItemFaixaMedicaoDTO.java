package br.gov.ms.aem.verificacaointermediariabalancaapi.domains.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ItemFaixaMedicaoDTO {
    private Integer id;
    private Double valorPadrao;
    private Double valorTolerancia;
    public FaixaMedicaoDTO faixaMedicao;
}
