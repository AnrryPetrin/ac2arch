package com.anrry.ac2arch.modules.especializacao;

import java.util.List;
import java.util.Optional;

public interface EspecializacaoService {
  /**
   * Salvar uma nova especialização.
   *
   * @param especializacao A especialização a ser salva.
   * @return A especialização salva.
   */
  Especializacao saveEspecializacao(Especializacao especializacao);

  /**
   * Obter uma especialização pelo ID.
   *
   * @param id O ID da especialização.
   * @return Um Optional contendo a especialização se encontrada.
   */
  Optional<Especializacao> getEspecializacaoById(Long id);

  /**
   * Obter todas as especializações.
   *
   * @return Uma lista de todas as especializações.
   */
  List<Especializacao> getAllEspecializacoes();

  /**
   * Deletar uma especialização pelo ID.
   *
   * @param id O ID da especialização a ser deletada.
   */
  void deleteEspecializacao(Long id);
}
