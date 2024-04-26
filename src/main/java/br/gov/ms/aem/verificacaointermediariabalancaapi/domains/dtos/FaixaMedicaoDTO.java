package br.gov.ms.aem.verificacaointermediariabalancaapi.domains.dtos;

import java.time.LocalDateTime;

import br.gov.ms.aem.verificacaointermediariabalancaapi.enums.Portaria;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FaixaMedicaoDTO {
    private Integer id;
    private String faixaMedicao;
    private Portaria portaria;
    private LocalDateTime dtCriacao;
    // private List<ItemFaixaMedicaoDTO> itens;
}
