package com.anrry.ac2arch.modules.resumotreinamento;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ResumoTreinamentoRepository extends JpaRepository<ResumoTreinamento, Long> {
  List<ResumoTreinamento> findByProfessorId(Long professorId);

  List<ResumoTreinamento> findByAgendaId(Long agendaId);
}
