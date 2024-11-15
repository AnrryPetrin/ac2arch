package com.anrry.ac2arch.modules.professor;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProfessorServiceImpl implements ProfessorService {
  @Autowired
  private ProfessorRepository professorRepository;

  @Override
  public Professor saveProfessor(Professor professor) {
    return professorRepository.save(professor);
  }

  @Override
  public Optional<Professor> getProfessorById(Long id) {
    return professorRepository.findById(id);
  }

  @Override
  public List<Professor> getAllProfessores() {
    return professorRepository.findAll();
  }

  @Override
  public void deleteProfessor(Long id) {
    professorRepository.deleteById(id);
  }

  @Override
  public Optional<Professor> getProfessorByCpf(String cpf) {
    return professorRepository.findByCpf(cpf);
  }

  @Override
  public List<Professor> getProfessoresByCursoId(Long cursoId) {
    return professorRepository.findByCursosId(cursoId);
  }
}
