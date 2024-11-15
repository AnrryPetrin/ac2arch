package com.anrry.ac2arch.modules.professor;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/professores")
public class ProfessorController {
  @Autowired
  private ProfessorService professorService;

  /**
   * Criar um novo Professor.
   * 
   * @param professor Objeto Professor a ser criado.
   * @return Professor criado com status 201.
   */
  @PostMapping
  public ResponseEntity<Professor> createProfessor(@RequestBody Professor professor) {
    Professor savedProfessor = professorService.saveProfessor(professor);
    return ResponseEntity.status(201).body(savedProfessor);
  }

  /**
   * Obter um Professor pelo ID.
   * 
   * @param id ID do Professor.
   * @return Professor encontrado ou 404 se não existir.
   */
  @GetMapping("/{id}")
  public ResponseEntity<Professor> getProfessorById(@PathVariable Long id) {
    Optional<Professor> professor = professorService.getProfessorById(id);
    return professor.map(ResponseEntity::ok)
        .orElse(ResponseEntity.notFound().build());
  }

  /**
   * Obter todos os Professores.
   * 
   * @return Lista de Professores.
   */
  @GetMapping
  public ResponseEntity<List<Professor>> getAllProfessores() {
    List<Professor> professores = professorService.getAllProfessores();
    return ResponseEntity.ok(professores);
  }

  /**
   * Deletar um Professor pelo ID.
   * 
   * @param id ID do Professor.
   * @return Status 204 No Content.
   */
  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteProfessor(@PathVariable Long id) {
    professorService.deleteProfessor(id);
    return ResponseEntity.noContent().build();
  }

  /**
   * Obter um Professor pelo CPF.
   * 
   * @param cpf CPF do Professor.
   * @return Professor encontrado ou 404 se não existir.
   */
  @GetMapping("/cpf/{cpf}")
  public ResponseEntity<Professor> getProfessorByCpf(@PathVariable String cpf) {
    Optional<Professor> professor = professorService.getProfessorByCpf(cpf);
    return professor.map(ResponseEntity::ok)
        .orElse(ResponseEntity.notFound().build());
  }

  /**
   * Obter Professores associados a um Curso específico.
   * 
   * @param cursoId ID do Curso.
   * @return Lista de Professores associados ao Curso.
   */
  @GetMapping("/curso/{cursoId}")
  public ResponseEntity<List<Professor>> getProfessoresByCursoId(@PathVariable Long cursoId) {
    List<Professor> professores = professorService.getProfessoresByCursoId(cursoId);
    return ResponseEntity.ok(professores);
  }
}
