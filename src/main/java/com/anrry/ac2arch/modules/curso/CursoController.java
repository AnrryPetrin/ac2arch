package com.anrry.ac2arch.modules.curso;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/cursos")
public class CursoController {
  @Autowired
  private CursoService cursoService;

  /**
   * Criar um novo Curso.
   * 
   * @param curso Objeto Curso a ser criado.
   * @return Curso criado com status 201.
   */
  @PostMapping
  public ResponseEntity<Curso> createCurso(@RequestBody Curso curso) {
    Curso savedCurso = cursoService.saveCurso(curso);
    return ResponseEntity.status(201).body(savedCurso);
  }

  /**
   * Obter um Curso pelo ID.
   * 
   * @param id ID do Curso.
   * @return Curso encontrado ou 404 se não existir.
   */
  @GetMapping("/{id}")
  public ResponseEntity<Curso> getCursoById(@PathVariable Long id) {
    Optional<Curso> curso = cursoService.getCursoById(id);
    return curso.map(ResponseEntity::ok)
        .orElse(ResponseEntity.notFound().build());
  }

  /**
   * Obter todos os Cursos.
   * 
   * @return Lista de Cursos.
   */
  @GetMapping
  public ResponseEntity<List<Curso>> getAllCursos() {
    List<Curso> cursos = cursoService.getAllCursos();
    return ResponseEntity.ok(cursos);
  }

  /**
   * Deletar um Curso pelo ID.
   * 
   * @param id ID do Curso.
   * @return Status 204 No Content.
   */
  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteCurso(@PathVariable Long id) {
    cursoService.deleteCurso(id);
    return ResponseEntity.noContent().build();
  }

  /**
   * Obter Cursos associados a um Professor específico.
   * 
   * @param professorId ID do Professor.
   * @return Lista de Cursos associados ao Professor.
   */
  @GetMapping("/professor/{professorId}")
  public ResponseEntity<List<Curso>> getCursosByProfessorId(@PathVariable Long professorId) {
    List<Curso> cursos = cursoService.getCursosByProfessorId(professorId);
    return ResponseEntity.ok(cursos);
  }

  /**
   * Buscar Cursos por descrição.
   * 
   * @param descricao Parte da descrição do Curso.
   * @return Lista de Cursos que contém a descrição fornecida.
   */
  @GetMapping("/search")
  public ResponseEntity<List<Curso>> searchCursosByDescricao(@RequestParam String descricao) {
    List<Curso> cursos = cursoService.searchCursosByDescricao(descricao);
    return ResponseEntity.ok(cursos);
  }
}
