package com.anrry.ac2arch.modules.especializacao;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

@Service
public class EspecializacaoServiceImpl implements EspecializacaoService {

  private final EspecializacaoRepository especializacaoRepository;

  public EspecializacaoServiceImpl(EspecializacaoRepository especializacaoRepository) {
    this.especializacaoRepository = especializacaoRepository;
  }

  /**
   * Salvar uma nova especialização.
   *
   * @param especializacao A especialização a ser salva.
   * @return A especialização salva.
   */
  @Override
  public Especializacao saveEspecializacao(Especializacao especializacao) {
    return especializacaoRepository.save(especializacao);
  }

  /**
   * Obter uma especialização pelo ID.
   *
   * @param id O ID da especialização.
   * @return Um Optional contendo a especialização se encontrada.
   */
  @Override
  public Optional<Especializacao> getEspecializacaoById(Long id) {
    return especializacaoRepository.findById(id);
  }

  /**
   * Obter todas as especializações.
   *
   * @return Uma lista de todas as especializações.
   */
  @Override
  public List<Especializacao> getAllEspecializacoes() {
    return especializacaoRepository.findAll();
  }

  /**
   * Deletar uma especialização pelo ID.
   *
   * @param id O ID da especialização a ser deletada.
   */
  @Override
  public void deleteEspecializacao(Long id) {
    especializacaoRepository.deleteById(id);
  }
}