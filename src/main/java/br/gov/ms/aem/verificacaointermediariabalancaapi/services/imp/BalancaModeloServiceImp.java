package br.gov.ms.aem.verificacaointermediariabalancaapi.services.imp;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.gov.ms.aem.verificacaointermediariabalancaapi.domains.dtos.BalancaModeloDTO;
import br.gov.ms.aem.verificacaointermediariabalancaapi.domains.entities.BalancaModelo;
import br.gov.ms.aem.verificacaointermediariabalancaapi.repositories.BalancaModeloRepository;
import br.gov.ms.aem.verificacaointermediariabalancaapi.services.BalancaModeloService;

@Service
public class BalancaModeloServiceImp implements BalancaModeloService {

    @Autowired
    BalancaModeloRepository repository;

    @Override
    public BalancaModeloDTO save(BalancaModeloDTO BalancaModeloDTO) {
        BalancaModelo balancaModelo = buildEntity(BalancaModeloDTO);
        repository.save(balancaModelo);
        return buildDTO(balancaModelo);
    }

    @Override
    public List<BalancaModeloDTO> findAll() {
        List<BalancaModelo> balancaModelos = repository.findAll();
        return buildDTOList(balancaModelos);
    }

    @Override
    public Boolean delete(Integer id) {
        Optional<BalancaModelo> balancaModeloOptional = repository.findById(id);
        boolean balancaModeloEncontrada = balancaModeloOptional.isPresent();
        balancaModeloOptional.ifPresent(repository::delete);
        return balancaModeloEncontrada;
    }

    @Override
    public BalancaModeloDTO update(Integer id, BalancaModeloDTO BalancaModeloDTO) {
        Optional<BalancaModelo> balancaModeloOptional = repository.findById(id);
        BalancaModelo balancaModeloBD = balancaModeloOptional.get();
        BalancaModelo balancaModeloRequest = buildEntity(BalancaModeloDTO);
        merge(balancaModeloBD, balancaModeloRequest);
        repository.save(balancaModeloRequest);
        return buildDTO(balancaModeloRequest);
    }

    @Override
    public BalancaModeloDTO findById(Integer id) {
        Optional<BalancaModelo> balancaModeloOptional = repository.findById(id);
        return buildDTO(balancaModeloOptional.get());
    }

    private BalancaModelo buildEntity(BalancaModeloDTO balancaModeloDTO) {
        if (balancaModeloDTO == null)
            throw new RuntimeException("Erro ao buildar Balança Modelo!");
        return BalancaModelo.builder()
                .id(balancaModeloDTO.getId())
                .dtCriacao(balancaModeloDTO.getDtCriacao())
                .marca(balancaModeloDTO.getMarca())
                .modelo(balancaModeloDTO.getModelo())
                .build();
    }

    private BalancaModeloDTO buildDTO(BalancaModelo balancaModelo) {
        if (balancaModelo == null)
            throw new RuntimeException("Erro ao buildar Balança Modelo!");
        return BalancaModeloDTO.builder()
                .id(balancaModelo.getId())
                .dtCriacao(balancaModelo.getDtCriacao())
                .marca(balancaModelo.getMarca())
                .modelo(balancaModelo.getModelo())
                .build();
    }

    private List<BalancaModeloDTO> buildDTOList(List<BalancaModelo> balancaModelos) {
        return balancaModelos.stream().map(this::buildDTO)
                .collect(Collectors.toList());
    }

    private BalancaModelo merge(BalancaModelo balancaModeloBD, BalancaModelo balancaModeloRequest) {
        if (balancaModeloBD == null || balancaModeloRequest == null)
            throw new RuntimeException("Erro ao atualizar Balança Modelo!");
        BeanUtils.copyProperties(balancaModeloRequest, balancaModeloBD);
        return balancaModeloRequest;
    }

}
