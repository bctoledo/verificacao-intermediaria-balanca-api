package br.gov.ms.aem.verificacaointermediariabalancaapi.services.imp;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.gov.ms.aem.verificacaointermediariabalancaapi.domains.dtos.BalancaDTO;
import br.gov.ms.aem.verificacaointermediariabalancaapi.domains.entities.Balanca;
import br.gov.ms.aem.verificacaointermediariabalancaapi.repositories.BalancaRepository;
import br.gov.ms.aem.verificacaointermediariabalancaapi.services.BalancaService;

@Service
public class BalancaServiceImp implements BalancaService {

    @Autowired
    BalancaRepository repository;

    @Override
    public BalancaDTO save(BalancaDTO balancaDTO) {
        Balanca balanca = buildEntity(balancaDTO);
        repository.save(balanca);
        return buildDTO(balanca);
    }

    @Override
    public List<BalancaDTO> findAll() {
        List<Balanca> balancas = repository.findAll();
        return buildDTOList(balancas);
    }

    @Override
    public Boolean delete(Integer id) {
        Optional<Balanca> balancaOptional = repository.findById(id);
        boolean balancaEncontrada = balancaOptional.isPresent();
        balancaOptional.ifPresent(repository::delete);
        return balancaEncontrada;
    }

    @Override
    public BalancaDTO update(Integer id, BalancaDTO balancaDTO) {
        Optional<Balanca> balancaOptional = repository.findById(id);
        Balanca balancaBD = balancaOptional.get();
        Balanca balancaRequest = buildEntity(balancaDTO);
        merge(balancaBD, balancaRequest);
        repository.save(balancaRequest);
        return buildDTO(balancaRequest);
    }

    @Override
    public BalancaDTO findById(Integer id) {
        Optional<Balanca> balancaOptional = repository.findById(id);
        return buildDTO(balancaOptional.get());
    }

    private Balanca buildEntity(BalancaDTO balancaDTO) {
        if (balancaDTO == null)
            throw new RuntimeException("Erro ao buildar Balanca!");
        return Balanca.builder()
                .id(balancaDTO.getId())
                .dtCriacao(balancaDTO.getDtCriacao())
                .numeroPatrimonio(balancaDTO.getNumeroPatrimonio())
                .numeroSerie(balancaDTO.getNumeroSerie())
                .build();
    }

    private BalancaDTO buildDTO(Balanca balanca) {
        if (balanca == null)
            throw new RuntimeException("Erro ao buildar Balança!");
        return BalancaDTO.builder()
                .id(balanca.getId())
                .dtCriacao(balanca.getDtCriacao())
                .numeroPatrimonio(balanca.getNumeroPatrimonio())
                .numeroSerie(balanca.getNumeroSerie())
                .build();
    }

    private List<BalancaDTO> buildDTOList(List<Balanca> balancas) {
        return balancas.stream().map(this::buildDTO)
                .collect(Collectors.toList());
    }

    private Balanca merge(Balanca balancaBD, Balanca balancaRequest) {
        if (balancaBD == null || balancaRequest == null)
            throw new RuntimeException("Erro ao atualizar Balança!");
        BeanUtils.copyProperties(balancaRequest, balancaBD);
        return balancaRequest;
    }

}
