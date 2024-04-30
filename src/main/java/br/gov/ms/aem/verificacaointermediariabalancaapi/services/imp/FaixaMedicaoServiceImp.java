package br.gov.ms.aem.verificacaointermediariabalancaapi.services.imp;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.gov.ms.aem.verificacaointermediariabalancaapi.domains.dtos.FaixaMedicaoDTO;
import br.gov.ms.aem.verificacaointermediariabalancaapi.domains.entities.FaixaMedicao;
import br.gov.ms.aem.verificacaointermediariabalancaapi.repositories.FaixaMedicaoRepository;
import br.gov.ms.aem.verificacaointermediariabalancaapi.services.FaixaMedicaoService;

@Service
public class FaixaMedicaoServiceImp implements FaixaMedicaoService {

    @Autowired
    FaixaMedicaoRepository repository;

    @Override
    public FaixaMedicaoDTO save(FaixaMedicaoDTO FaixaMedicaoDTO) {
        FaixaMedicao faixaMedicao = buildEntity(FaixaMedicaoDTO);
        repository.save(faixaMedicao);
        return buildDTO(faixaMedicao);
    }

    @Override
    public List<FaixaMedicaoDTO> findAll() {
        List<FaixaMedicao> faixaMedicoes = repository.findAll();
        return buildDTOList(faixaMedicoes);
    }

    @Override
    public Boolean delete(Integer id) {
        Optional<FaixaMedicao> faixaMedicoesOptional = repository.findById(id);
        boolean faixaMedicoesModeloEncontrada = faixaMedicoesOptional.isPresent();
        faixaMedicoesOptional.ifPresent(repository::delete);
        return faixaMedicoesModeloEncontrada;
    }

    @Override
    public FaixaMedicaoDTO update(Integer id, FaixaMedicaoDTO FaixaMedicaoDTO) {
        Optional<FaixaMedicao> faixaMedicoesOptional = repository.findById(id);
        FaixaMedicao faixaMedicoesBD = faixaMedicoesOptional.get();
        FaixaMedicao faixaMedicoesRequest = buildEntity(FaixaMedicaoDTO);
        merge(faixaMedicoesBD, faixaMedicoesRequest);
        repository.save(faixaMedicoesRequest);
        return buildDTO(faixaMedicoesRequest);
    }

    @Override
    public FaixaMedicaoDTO findById(Integer id) {
        Optional<FaixaMedicao> faixaMedicoesOptional = repository.findById(id);
        return buildDTO(faixaMedicoesOptional.get());
    }

    private FaixaMedicao buildEntity(FaixaMedicaoDTO faixaMedicaoDTO) {
        if (faixaMedicaoDTO == null)
            throw new RuntimeException("Erro ao buildar Balança Modelo!");
        return FaixaMedicao.builder()
                .id(faixaMedicaoDTO.getId())
                .dtCriacao(faixaMedicaoDTO.getDtCriacao())
                .faixaMedicao(faixaMedicaoDTO.getFaixaMedicao())
                .portaria(faixaMedicaoDTO.getPortaria())
                .build();
    }

    private FaixaMedicaoDTO buildDTO(FaixaMedicao faixaMedicao) {
        if (faixaMedicao == null)
            throw new RuntimeException("Erro ao buildar Faixa de Medição!");
        return FaixaMedicaoDTO.builder()
                .id(faixaMedicao.getId())
                .dtCriacao(faixaMedicao.getDtCriacao())
                .faixaMedicao(faixaMedicao.getFaixaMedicao())
                .portaria(faixaMedicao.getPortaria())
                .build();
    }

    private List<FaixaMedicaoDTO> buildDTOList(List<FaixaMedicao> faixaMedicoes) {
        return faixaMedicoes.stream().map(this::buildDTO)
                .collect(Collectors.toList());
    }

    private FaixaMedicao merge(FaixaMedicao faixaMedicoesBD, FaixaMedicao faixaMedicoesRequest) {
        if (faixaMedicoesBD == null || faixaMedicoesRequest == null)
            throw new RuntimeException("Erro ao atualizar Faixa de Medição!");
        BeanUtils.copyProperties(faixaMedicoesRequest, faixaMedicoesBD);
        return faixaMedicoesRequest;
    }

}
