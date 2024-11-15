package com.anrry.ac2arch.modules.especializacao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EspecializacaoRepository extends JpaRepository<Especializacao, Long> {

}