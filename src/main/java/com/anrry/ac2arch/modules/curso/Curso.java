package com.anrry.ac2arch.modules.curso;

import java.util.Set;

import com.anrry.ac2arch.modules.professor.Professor;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Curso {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String descricao;
  private Integer cargaHoraria;
  private String objetivos;
  private String ementa;

  @ManyToMany(mappedBy = "cursos")
  private Set<Professor> professores;
}
