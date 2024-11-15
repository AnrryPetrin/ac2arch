package com.anrry.ac2arch.modules.professor;

import java.util.List;
import java.util.Optional;

public interface ProfessorService {
  Professor saveProfessor(Professor professor);

  Optional<Professor> getProfessorById(Long id);

  List<Professor> getAllProfessores();

  void deleteProfessor(Long id);

  Optional<Professor> getProfessorByCpf(String cpf);

  List<Professor> getProfessoresByCursoId(Long cursoId);
}
