package com.anrry.ac2arch.modules.curso;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CursoServiceImpl implements CursoService {
  @Autowired
  private CursoRepository cursoRepository;

  @Override
  public Curso saveCurso(Curso curso) {
    return cursoRepository.save(curso);
  }

  @Override
  public Optional<Curso> getCursoById(Long id) {
    return cursoRepository.findById(id);
  }

  @Override
  public List<Curso> getAllCursos() {
    return cursoRepository.findAll();
  }

  @Override
  public void deleteCurso(Long id) {
    cursoRepository.deleteById(id);
  }

  @Override
  public List<Curso> getCursosByProfessorId(Long professorId) {
    return cursoRepository.findByProfessoresId(professorId);
  }

  @Override
  public List<Curso> searchCursosByDescricao(String descricao) {
    return cursoRepository.findByDescricaoContainingIgnoreCase(descricao);
  }
}
