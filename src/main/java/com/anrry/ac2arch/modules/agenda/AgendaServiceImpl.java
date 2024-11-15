package com.anrry.ac2arch.modules.agenda;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.anrry.ac2arch.modules.professor.Professor;

import jakarta.transaction.Transactional;

@Service
public class AgendaServiceImpl implements AgendaService {
  @Autowired
  private AgendaRepository agendaRepository;

  @Transactional
  @Override
  public Agenda saveAgenda(Agenda agenda) throws IllegalArgumentException {
    // Validação de conflito de horários
    Professor professor = agenda.getProfessor();
    List<Agenda> agendasConflitantes = agendaRepository
        .findByProfessorAndDataInicioLessThanEqualAndDataFimGreaterThanEqual(
            professor, agenda.getDataFim(), agenda.getDataInicio());

    // Verificar sobreposição de horários
    for (Agenda existente : agendasConflitantes) {
      if (horariosConflitam(agenda.getHorarioInicio(), agenda.getHorarioFim(),
          existente.getHorarioInicio(), existente.getHorarioFim())) {
        throw new IllegalArgumentException("O professor já está agendado para outro treinamento nesse período.");
      }
    }

    return agendaRepository.save(agenda);
  }

  private boolean horariosConflitam(
      java.time.LocalTime inicio1, java.time.LocalTime fim1,
      java.time.LocalTime inicio2, java.time.LocalTime fim2) {
    return inicio1.isBefore(fim2) && inicio2.isBefore(fim1);
  }

  @Override
  public Optional<Agenda> getAgendaById(Long id) {
    return agendaRepository.findById(id);
  }

  @Override
  public List<Agenda> getAllAgendas() {
    return agendaRepository.findAll();
  }

  @Override
  public void deleteAgenda(Long id) {
    agendaRepository.deleteById(id);
  }

  @Override
  public List<Agenda> findByProfessorAndDateRange(Professor professor, LocalDate dataInicio, LocalDate dataFim) {
    return agendaRepository.findByProfessorAndDataInicioLessThanEqualAndDataFimGreaterThanEqual(professor, dataFim,
        dataInicio);
  }

  @Override
  public List<Agenda> findByCursoId(Long cursoId) {
    return agendaRepository.findByCursoId(cursoId);
  }
}
