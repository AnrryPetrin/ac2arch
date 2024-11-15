package com.anrry.ac2arch.modules.professor;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfessorRepository extends JpaRepository<Professor, Long> {
  Optional<Professor> findByCpf(String cpf);

  List<Professor> findByCursosId(Long cursoId);
}
