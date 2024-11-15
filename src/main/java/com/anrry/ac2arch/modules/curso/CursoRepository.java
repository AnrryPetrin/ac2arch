package com.anrry.ac2arch.modules.curso;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CursoRepository extends JpaRepository<Curso, Long> {
  List<Curso> findByDescricaoContainingIgnoreCase(String descricao);

  List<Curso> findByProfessoresId(Long professorId);
}
