package com.anrry.ac2arch.modules.curso;

import java.util.List;
import java.util.Optional;

public interface CursoService {
  Curso saveCurso(Curso curso);

  Optional<Curso> getCursoById(Long id);

  List<Curso> getAllCursos();

  void deleteCurso(Long id);

  List<Curso> getCursosByProfessorId(Long professorId);

  List<Curso> searchCursosByDescricao(String descricao);
}
