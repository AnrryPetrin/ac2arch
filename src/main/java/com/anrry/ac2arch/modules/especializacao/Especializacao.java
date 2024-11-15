package com.anrry.ac2arch.modules.especializacao;

import java.util.Set;

import com.anrry.ac2arch.modules.professor.Professor;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "especializacoes")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Especializacao {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @NotBlank(message = "Nome da especialização é obrigatório.")
  private String nome;

  private String descricao;

  @ManyToMany(mappedBy = "especializacoes")
  private Set<Professor> professores;
}