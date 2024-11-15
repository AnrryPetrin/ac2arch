package com.anrry.ac2arch.modules.agenda;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.anrry.ac2arch.modules.professor.Professor;

public interface AgendaRepository extends JpaRepository<Agenda, Long> {

  List<Agenda> findByProfessorAndDataInicioLessThanEqualAndDataFimGreaterThanEqual(
      Professor professor, LocalDate dataFim, LocalDate dataInicio);

  List<Agenda> findByCursoId(Long cursoId);
}
