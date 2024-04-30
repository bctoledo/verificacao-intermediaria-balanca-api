package br.gov.ms.aem.verificacaointermediariabalancaapi.services.imp;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.gov.ms.aem.verificacaointermediariabalancaapi.domains.dtos.ItemFaixaMedicaoDTO;
import br.gov.ms.aem.verificacaointermediariabalancaapi.domains.entities.ItemFaixaMedicao;
import br.gov.ms.aem.verificacaointermediariabalancaapi.repositories.ItemFaixaMedicaoRepository;
import br.gov.ms.aem.verificacaointermediariabalancaapi.services.ItemFaixaMedicaoService;

@Service
public class ItemFaixaMedicaoServiceImp implements ItemFaixaMedicaoService {

    @Autowired
    ItemFaixaMedicaoRepository repository;

    @Override
    public ItemFaixaMedicaoDTO save(ItemFaixaMedicaoDTO ItemFaixaMedicaoDTO) {
        ItemFaixaMedicao itemFaixaMedicao = buildEntity(ItemFaixaMedicaoDTO);
        repository.save(itemFaixaMedicao);
        return buildDTO(itemFaixaMedicao);
    }

    @Override
    public List<ItemFaixaMedicaoDTO> findAll() {
        List<ItemFaixaMedicao> itemFaixaMedicoes = repository.findAll();
        return buildDTOList(itemFaixaMedicoes);
    }

    @Override
    public Boolean delete(Integer id) {
        Optional<ItemFaixaMedicao> itemFaixaMedicoesOptional = repository.findById(id);
        boolean itemFaixaMedicoesEncontrada = itemFaixaMedicoesOptional.isPresent();
        itemFaixaMedicoesOptional.ifPresent(repository::delete);
        return itemFaixaMedicoesEncontrada;
    }

    @Override
    public ItemFaixaMedicaoDTO update(Integer id, ItemFaixaMedicaoDTO ItemFaixaMedicaoDTO) {
        Optional<ItemFaixaMedicao> itemFaixaMedicoesOptional = repository.findById(id);
        ItemFaixaMedicao itemFaixaMedicoesBD = itemFaixaMedicoesOptional.get();
        ItemFaixaMedicao itemFaixaMedicoesRequest = buildEntity(ItemFaixaMedicaoDTO);
        merge(itemFaixaMedicoesBD, itemFaixaMedicoesRequest);
        repository.save(itemFaixaMedicoesRequest);
        return buildDTO(itemFaixaMedicoesRequest);
    }

    @Override
    public ItemFaixaMedicaoDTO findById(Integer id) {
        Optional<ItemFaixaMedicao> itemFaixaMedicoesOptional = repository.findById(id);
        return buildDTO(itemFaixaMedicoesOptional.get());
    }

    private ItemFaixaMedicao buildEntity(ItemFaixaMedicaoDTO itemFaixaMedicaoDTO) {
        if (itemFaixaMedicaoDTO == null)
            throw new RuntimeException("Erro ao buildar Balança Modelo!");
        return ItemFaixaMedicao.builder()
                .id(itemFaixaMedicaoDTO.getId())
                // .faixaMedicao(itemFaixaMedicaoDTO.getFaixaMedicao())
                .valorPadrao(itemFaixaMedicaoDTO.getValorPadrao())
                .valorTolerancia(itemFaixaMedicaoDTO.getValorTolerancia())
                .build();
    }

    private ItemFaixaMedicaoDTO buildDTO(ItemFaixaMedicao itemFaixaMedicao) {
        if (itemFaixaMedicao == null)
            throw new RuntimeException("Erro ao buildar Item Faixa de Medição!");
        return ItemFaixaMedicaoDTO.builder()
                .id(itemFaixaMedicao.getId())
                // .faixaMedicao(itemFaixaMedicao.getFaixaMedicao())
                .valorPadrao(itemFaixaMedicao.getValorPadrao())
                .valorTolerancia(itemFaixaMedicao.getValorTolerancia())
                .build();
    }

    private List<ItemFaixaMedicaoDTO> buildDTOList(List<ItemFaixaMedicao> itemFaixaMedicoes) {
        return itemFaixaMedicoes.stream().map(this::buildDTO)
                .collect(Collectors.toList());
    }

    private ItemFaixaMedicao merge(ItemFaixaMedicao itemFaixaMedicoesBD, ItemFaixaMedicao itemFaixaMedicoesRequest) {
        if (itemFaixaMedicoesBD == null || itemFaixaMedicoesRequest == null)
            throw new RuntimeException("Erro ao atualizar Item Faixa de Medição!");
        BeanUtils.copyProperties(itemFaixaMedicoesRequest, itemFaixaMedicoesBD);
        return itemFaixaMedicoesRequest;
    }

}
