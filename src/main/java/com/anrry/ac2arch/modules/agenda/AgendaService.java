package com.anrry.ac2arch.modules.agenda;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import com.anrry.ac2arch.modules.professor.Professor;

public interface AgendaService {
  Agenda saveAgenda(Agenda agenda) throws IllegalArgumentException;

  Optional<Agenda> getAgendaById(Long id);

  List<Agenda> getAllAgendas();

  void deleteAgenda(Long id);

  List<Agenda> findByProfessorAndDateRange(Professor professor, LocalDate dataInicio, LocalDate dataFim);

  List<Agenda> findByCursoId(Long cursoId);
}
