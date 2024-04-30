package br.gov.ms.aem.verificacaointermediariabalancaapi.domains.entities;

import java.time.LocalDateTime;
import java.util.List;

import br.gov.ms.aem.verificacaointermediariabalancaapi.domains.dtos.FaixaMedicaoDTO;
import br.gov.ms.aem.verificacaointermediariabalancaapi.domains.enums.Portaria;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FaixaMedicao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String faixaMedicao;

    @Enumerated(EnumType.STRING)
    private Portaria portaria;

    private LocalDateTime dtCriacao;

    @OneToMany(mappedBy = "faixaMedicao")
    private List<ItemFaixaMedicao> itens;

    public static FaixaMedicao buildEntity(FaixaMedicaoDTO faixaMedicaoDTO) {
        if (faixaMedicaoDTO == null)
            throw new RuntimeException("Erro ao buildar Balança Modelo!");
        return FaixaMedicao.builder()
                .id(faixaMedicaoDTO.getId())
                .dtCriacao(faixaMedicaoDTO.getDtCriacao())
                .faixaMedicao(faixaMedicaoDTO.getFaixaMedicao())
                .portaria(faixaMedicaoDTO.getPortaria())
                .build();
    }

}
