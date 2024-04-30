package br.gov.ms.aem.verificacaointermediariabalancaapi.domains.dtos;

import java.time.LocalDateTime;

import br.gov.ms.aem.verificacaointermediariabalancaapi.domains.entities.FaixaMedicao;
import br.gov.ms.aem.verificacaointermediariabalancaapi.domains.enums.Portaria;
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

    public static FaixaMedicaoDTO buildDTO(FaixaMedicao faixaMedicao) {
        if (faixaMedicao == null)
            throw new RuntimeException("Erro ao buildar Faixa de Medição!");
        return FaixaMedicaoDTO.builder()
                .id(faixaMedicao.getId())
                .dtCriacao(faixaMedicao.getDtCriacao())
                .faixaMedicao(faixaMedicao.getFaixaMedicao())
                .portaria(faixaMedicao.getPortaria())
                .build();
    }
}
