package com.anrry.ac2arch.modules.resumotreinamento;

import java.util.List;
import java.util.Optional;

public interface ResumoTreinamentoService {
  ResumoTreinamento saveResumo(ResumoTreinamento resumo);

  Optional<ResumoTreinamento> getResumoById(Long id);

  List<ResumoTreinamento> getAllResumos();

  void deleteResumo(Long id);

  List<ResumoTreinamento> getResumosByProfessorId(Long professorId);

  List<ResumoTreinamento> getResumosByAgendaId(Long agendaId);
}
