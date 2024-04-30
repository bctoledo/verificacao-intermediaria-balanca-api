package br.gov.ms.aem.verificacaointermediariabalancaapi.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.gov.ms.aem.verificacaointermediariabalancaapi.domains.dtos.BalancaDTO;
import br.gov.ms.aem.verificacaointermediariabalancaapi.services.BalancaService;

@RestController
@RequestMapping("/balancas")
public class BalancaController {

    @Autowired
    private BalancaService service;

    @GetMapping
    public ResponseEntity<List<BalancaDTO>> findAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<BalancaDTO> findById(@PathVariable Integer id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @PostMapping
    public ResponseEntity<BalancaDTO> save(@RequestBody BalancaDTO balancaDTO) {
        return ResponseEntity.ok(service.save(balancaDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<BalancaDTO> update(@PathVariable Integer id, @RequestBody BalancaDTO balancaDTO) {
        return ResponseEntity.ok(service.update(id, balancaDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable Integer id) {
        return ResponseEntity.ok(service.delete(id));
    }

}
