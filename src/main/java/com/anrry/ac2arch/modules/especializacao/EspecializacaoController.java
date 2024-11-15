package com.anrry.ac2arch.modules.especializacao;

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

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/especializacoes")
public class EspecializacaoController {

  @Autowired
  private EspecializacaoService especializacaoService;

  /**
   * Criar uma nova Especialização.
   *
   * @param especializacao A especialização a ser criada.
   * @return A especialização criada com status 201.
   */
  @PostMapping
  public ResponseEntity<Especializacao> createEspecializacao(@Valid @RequestBody Especializacao especializacao) {
    Especializacao savedEspecializacao = especializacaoService.saveEspecializacao(especializacao);
    return ResponseEntity.status(201).body(savedEspecializacao);
  }

  /**
   * Obter uma Especialização pelo ID.
   *
   * @param id O ID da especialização.
   * @return A especialização encontrada ou 404 se não encontrada.
   */
  @GetMapping("/{id}")
  public ResponseEntity<Especializacao> getEspecializacaoById(@PathVariable Long id) {
    Optional<Especializacao> especializacaoOpt = especializacaoService.getEspecializacaoById(id);
    return especializacaoOpt.map(ResponseEntity::ok)
        .orElseGet(() -> ResponseEntity.notFound().build());
  }

  /**
   * Obter todas as Especializações.
   *
   * @return Uma lista de todas as especializações.
   */
  @GetMapping
  public ResponseEntity<List<Especializacao>> getAllEspecializacoes() {
    List<Especializacao> especializacoes = especializacaoService.getAllEspecializacoes();
    return ResponseEntity.ok(especializacoes);
  }

  /**
   * Deletar uma Especialização pelo ID.
   *
   * @param id O ID da especialização a ser deletada.
   * @return Status 204 No Content se deletado com sucesso.
   */
  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteEspecializacao(@PathVariable Long id) {
    especializacaoService.deleteEspecializacao(id);
    return ResponseEntity.noContent().build();
  }
}
