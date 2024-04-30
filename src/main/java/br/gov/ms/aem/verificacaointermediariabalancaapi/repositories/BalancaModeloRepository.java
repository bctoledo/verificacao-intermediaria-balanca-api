package br.gov.ms.aem.verificacaointermediariabalancaapi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.gov.ms.aem.verificacaointermediariabalancaapi.domains.entities.BalancaModelo;

@Repository
public interface BalancaModeloRepository extends JpaRepository<BalancaModelo, Integer> {

}
