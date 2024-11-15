package com.anrry.ac2arch.modules.resumotreinamento;

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

import com.anrry.ac2arch.modules.agenda.Agenda;
import com.anrry.ac2arch.modules.agenda.AgendaService;
import com.anrry.ac2arch.modules.professor.Professor;
import com.anrry.ac2arch.modules.professor.ProfessorService;

@RestController
@RequestMapping("/api/resumos")
public class ResumoTreinamentoController {

  @Autowired
  private ResumoTreinamentoService resumoService;

  @Autowired
  private AgendaService agendaService;

  @Autowired
  private ProfessorService professorService;

  /**
   * Criar um novo Resumo de Treinamento.
   * 
   * @param resumo ResumoTreinamento a ser criado.
   * @return Resumo criado com status 201 ou erro 400 em caso de validação.
   */
  @PostMapping
  public ResponseEntity<?> createResumo(@RequestBody ResumoTreinamento resumo) {
    try {
      // Validação: Verificar se a Agenda existe
      Long agendaId = resumo.getAgenda().getId();
      Optional<Agenda> agendaOpt = agendaService.getAgendaById(agendaId);
      if (!agendaOpt.isPresent()) {
        return ResponseEntity.status(400).body("Agenda com ID " + agendaId + " não encontrada.");
      }

      // Validação: Verificar se o Professor existe
      Long professorId = resumo.getProfessor().getId();
      Optional<Professor> professorOpt = professorService.getProfessorById(professorId);
      if (!professorOpt.isPresent()) {
        return ResponseEntity.status(400).body("Professor com ID " + professorId + " não encontrado.");
      }

      // Associar Agenda e Professor existentes ao Resumo
      resumo.setAgenda(agendaOpt.get());
      resumo.setProfessor(professorOpt.get());

      ResumoTreinamento savedResumo = resumoService.saveResumo(resumo);
      return ResponseEntity.status(201).body(savedResumo);
    } catch (Exception e) {
      return ResponseEntity.status(500)
          .body("Erro ao criar resumo de treinamento: " + e.getMessage());
    }
  }

  /**
   * Obter um Resumo de Treinamento pelo ID.
   * 
   * @param id ID do Resumo.
   * @return Resumo encontrado ou 404 se não existir.
   */
  @GetMapping("/{id}")
  public ResponseEntity<ResumoTreinamento> getResumoById(@PathVariable Long id) {
    Optional<ResumoTreinamento> resumo = resumoService.getResumoById(id);
    return resumo.map(ResponseEntity::ok)
        .orElse(ResponseEntity.notFound().build());
  }

  /**
   * Obter todos os Resumos de Treinamento.
   * 
   * @return Lista de Resumos.
   */
  @GetMapping
  public ResponseEntity<List<ResumoTreinamento>> getAllResumos() {
    List<ResumoTreinamento> resumos = resumoService.getAllResumos();
    return ResponseEntity.ok(resumos);
  }

  /**
   * Deletar um Resumo de Treinamento pelo ID.
   * 
   * @param id ID do Resumo.
   * @return Status 204 No Content.
   */
  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteResumo(@PathVariable Long id) {
    resumoService.deleteResumo(id);
    return ResponseEntity.noContent().build();
  }

  /**
   * Obter Resumos de Treinamento associados a um Professor específico.
   * 
   * @param professorId ID do Professor.
   * @return Lista de Resumos associados ao Professor.
   */
  @GetMapping("/professor/{professorId}")
  public ResponseEntity<List<ResumoTreinamento>> getResumosByProfessorId(@PathVariable Long professorId) {
    List<ResumoTreinamento> resumos = resumoService.getResumosByProfessorId(professorId);
    return ResponseEntity.ok(resumos);
  }

  /**
   * Obter Resumos de Treinamento associados a uma Agenda específica.
   * 
   * @param agendaId ID da Agenda.
   * @return Lista de Resumos associados à Agenda.
   */
  @GetMapping("/agenda/{agendaId}")
  public ResponseEntity<List<ResumoTreinamento>> getResumosByAgendaId(@PathVariable Long agendaId) {
    List<ResumoTreinamento> resumos = resumoService.getResumosByAgendaId(agendaId);
    return ResponseEntity.ok(resumos);
  }
}